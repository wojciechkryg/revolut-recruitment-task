package com.wojdor.common_android.view

import android.content.Context
import android.graphics.Rect
import android.text.method.DigitsKeyListener
import android.util.AttributeSet
import android.widget.EditText
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import com.wojdor.common.extension.*
import com.wojdor.common.util.BigDecimalFormatter.separator
import com.wojdor.common_android.R
import com.wojdor.common_android.extension.showKeyboard
import java.math.BigDecimal

class CurrencyEditText(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) :
    EditText(context, attrs, defStyleAttr, defStyleRes) {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, R.attr.editTextStyle)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        DEFAULT_STYLE_RES
    )

    private var hadMaximumDecimalNumbersBeforeTextChange = false
    private var textBeforeTextChange = String.empty
    var onUserInput: (BigDecimal) -> Unit = {}

    init {
        keyListener = DigitsKeyListener.getInstance(ACCEPTED_CHARACTERS)
        doBeforeTextChanged { text, _, _, _ -> beforeTextChange(text.toString()) }
        doOnTextChanged { text, startPosition, lengthBefore, lengthAfter ->
            onTextChange(text.toString(), startPosition, lengthBefore, lengthAfter)
        }
    }

    private fun beforeTextChange(text: String) {
        hadMaximumDecimalNumbersBeforeTextChange = hasMaximumNumberOfDecimalNumbers(text)
        textBeforeTextChange = text
    }

    private fun hasMaximumNumberOfDecimalNumbers(input: String) =
        input.substringAfter(separator).length == MAX_DECIMAL_NUMBERS_COUNT

    private fun onTextChange(
        input: String,
        startPosition: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        if (hasFocus()) {
            onHasFocusTextChange(input, startPosition, lengthBefore, lengthAfter)
        }
    }

    private fun onHasFocusTextChange(
        input: String,
        startPosition: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        if (isInputWithMoreDecimalNumbers(input)) {
            setText(textBeforeTextChange)
            setSelection(startPosition)
        } else {
            onUserInputChanged(input, startPosition, lengthBefore, lengthAfter)
        }
    }

    private fun isInputWithMoreDecimalNumbers(input: String) =
        hadMaximumDecimalNumbersBeforeTextChange && hasTooManyDecimalNumbers(input)

    private fun onUserInputChanged(
        input: String,
        position: Int,
        changeCountBeforePosition: Int,
        changeCountAfterPosition: Int
    ) {
        when {
            //TODO: Check if text is too long, max 11 with spaces, 12 with separator
            hasWrongSeparator(input) -> applyProperSeparator(input)
            hasTooManySeparators(input) -> deleteAdditionalSeparators(input)
            isSeparatorFirst(input) -> addZeroAsPrefix(input)
            else -> formatInput(
                input,
                position,
                changeCountBeforePosition,
                changeCountAfterPosition
            )
        }
    }

    private fun hasWrongSeparator(input: String) = when {
        isWrongSeparator(Char.dot) -> input.contains(Char.dot)
        isWrongSeparator(Char.comma) -> input.contains(Char.comma)
        else -> false
    }

    private fun isWrongSeparator(possibleSeparator: Char) = possibleSeparator != separator

    private fun applyProperSeparator(input: String) {
        val lastSelectionEnd = selectionEnd
        val inputWithProperSeparator = when {
            input.contains(Char.dot) -> input.replace(Char.dot, separator)
            input.contains(Char.comma) -> input.replace(Char.comma, separator)
            else -> input
        }
        setText(inputWithProperSeparator)
        val selection = when {
            hasTooManySeparators(inputWithProperSeparator) ->
                inputWithProperSeparator.indexOfLast { it == separator }
            else -> lastSelectionEnd
        }
        setSelection(selection)
    }

    private fun hasTooManySeparators(input: String) =
        input.count { it == separator } > MAX_SEPARATOR_COUNT

    private fun deleteAdditionalSeparators(input: String) {
        val inputWithProperSeparatorCount =
            input.substringTo(input.indexOfLast { it == separator })
        setText(inputWithProperSeparatorCount)
        setSelection(inputWithProperSeparatorCount.length)
    }

    private fun hasTooManyDecimalNumbers(input: String) =
        input.contains(separator) &&
                input.substringAfter(separator).length > MAX_DECIMAL_NUMBERS_COUNT

    private fun isSeparatorFirst(input: String) =
        input.isNotEmpty() && input.first() == separator

    private fun addZeroAsPrefix(input: String) {
        val inputWithPrefix = "${Int.zero}$input"
        setText(inputWithPrefix)
        setSelection(inputWithPrefix.length)
    }

    private fun formatInput(
        input: String,
        position: Int,
        changeCountBeforePosition: Int,
        changeCountAfterPosition: Int
    ) {
        val lastSelectionStart = selectionStart
        val lastSelectionEnd = selectionEnd
        val inputFormatted = input.formatToTwoDecimalPlacesString()
        if (isInputNotFormatted(inputFormatted, input)) {
            applyFormattedText(
                inputFormatted,
                position,
                changeCountBeforePosition,
                changeCountAfterPosition
            )
            restoreSelection(inputFormatted, input, lastSelectionStart, lastSelectionEnd)
        } else {
            onUserInput(input.toBigDecimal())
        }
    }

    private fun applyFormattedText(
        inputFormatted: String,
        position: Int,
        changeCountBeforePosition: Int,
        changeCountAfterPosition: Int
    ) {
        if (isSpaceDeleted(
                inputFormatted,
                position,
                changeCountBeforePosition,
                changeCountAfterPosition
            )
        ) {
            val inputFormattedWithDeletedSpace =
                inputFormatted.removeRange(position - ONE_CHANGE, position)
                    .formatToTwoDecimalPlacesString()
            setText(inputFormattedWithDeletedSpace)
        } else {
            setText(inputFormatted)
        }
    }

    private fun isSpaceDeleted(
        inputFormatted: String,
        position: Int,
        changeCountBeforePosition: Int,
        changeCountAfterPosition: Int
    ) =
        changeCountBeforePosition == ONE_CHANGE
                && changeCountAfterPosition == NO_CHANGES
                && inputFormatted.length > position
                && inputFormatted[position] == Char.nonBreakingSpace

    private fun restoreSelection(
        inputFormatted: String,
        input: String,
        lastSelectionStart: Int,
        lastSelectionEnd: Int
    ) {
        // TODO: Selection not working properly
        if (lastSelectionEnd == input.length) {
            setSelection(inputFormatted.length, inputFormatted.length)
        } else {
            setSelection(lastSelectionStart, lastSelectionStart)
        }
    }

    private fun isInputNotFormatted(inputFormatted: String, input: String) =
        if (input.contains(separator)) {
            val inputWithoutSeparator = input.substringTo(input.indexOfFirst { it == separator })
            val inputFormattedWithoutSeparator = formatInputWithoutSeparator(inputFormatted, input)
            inputWithoutSeparator != String.empty
                    && inputWithoutSeparator != inputFormattedWithoutSeparator
        } else {
            input != String.empty && input != inputFormatted
        }

    private fun formatInputWithoutSeparator(inputFormatted: String, input: String) =
        if (inputFormatted.contains(separator)) {
            inputFormatted.substringTo(input.indexOfFirst { it == separator })
        } else {
            inputFormatted
        }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (focused) {
            setSelection(text.length)
            showKeyboard()
        }
    }

    companion object {
        const val DEFAULT_STYLE_RES = 0
        const val ACCEPTED_CHARACTERS = "0123456789,."
        const val MAX_SEPARATOR_COUNT = 1
        const val MAX_DECIMAL_NUMBERS_COUNT = 2
        const val NO_CHANGES = 0
        const val ONE_CHANGE = 1
    }
}