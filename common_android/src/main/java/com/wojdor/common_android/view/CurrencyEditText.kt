package com.wojdor.common_android.view

import android.content.Context
import android.graphics.Rect
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import com.wojdor.common.extension.*
import com.wojdor.common.util.BigDecimalFormatter.separator
import com.wojdor.common_android.R
import com.wojdor.common_android.extension.hideKeyboard
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
    private var hadMaximumSeparatorsBeforeTextChange = false
    private var textBeforeTextChange = String.empty
    var onUserInput: (BigDecimal) -> Unit = {}

    init {
        keyListener = DigitsKeyListener.getInstance(ACCEPTED_CHARACTERS)
        setRawInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL)
        imeOptions = EditorInfo.IME_FLAG_NO_EXTRACT_UI
        importantForAutofill = View.IMPORTANT_FOR_AUTOFILL_NO
        hint = "${Int.zero}"
        doBeforeTextChanged { text, _, _, _ -> beforeTextChange(text.toString()) }
        doOnTextChanged { text, startPosition, lengthBefore, lengthAfter ->
            onTextChange(text.toString(), startPosition, lengthBefore, lengthAfter)
        }
    }

    private fun beforeTextChange(text: String) {
        hadMaximumDecimalNumbersBeforeTextChange = hasMaximumNumberOfDecimalNumbers(text)
        hadMaximumSeparatorsBeforeTextChange = hasMaximumNumberOfSeparators(text)
        textBeforeTextChange = text
    }

    private fun hasMaximumNumberOfDecimalNumbers(input: String) =
        input.substringAfter(separator).length >= MAX_DECIMAL_NUMBERS_COUNT

    private fun hasMaximumNumberOfSeparators(input: String) =
        input.count { it == separator } >= MAX_SEPARATOR_COUNT

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
        when {
            shouldTextNotChange(input) ->
                setTextAndSelection(textBeforeTextChange, startPosition)
            else -> onUserInputChanged(input, startPosition, lengthBefore, lengthAfter)
        }
    }

    private fun shouldTextNotChange(input: String) =
        isInputTooLong(input)
                || isInputWithMoreDecimalNumbers(input)
                || isInputWithMorePossibleSeparators(input)

    private fun isInputTooLong(input: String) =
        isInputWithoutSeparatorTooLong(input) || isInputWithSeparatorTooLong(input)

    private fun isInputWithoutSeparatorTooLong(input: String) =
        input.none { it == Char.dot || it == Char.comma }
                && input.length > MAX_LENGTH_WITHOUT_SEPARATOR

    private fun isInputWithSeparatorTooLong(input: String) =
        input.any { it == Char.dot || it == Char.comma }
                && isNotPossibleToAddMoreCharactersWithSeparator(input)

    private fun isNotPossibleToAddMoreCharactersWithSeparator(input: String) =
        isSeparatorAsLastCharacterForLengthWithoutSeparator(input)
                || input.length > MAX_LENGTH_WITH_SEPARATOR

    private fun isSeparatorAsLastCharacterForLengthWithoutSeparator(input: String) =
        input.indexOfFirst { it == Char.dot || it == Char.comma } == MAX_LENGTH_WITHOUT_SEPARATOR

    private fun isInputWithMoreDecimalNumbers(input: String) =
        hadMaximumDecimalNumbersBeforeTextChange && hasTooManyDecimalNumbers(input)

    private fun isInputWithMorePossibleSeparators(input: String) =
        hadMaximumSeparatorsBeforeTextChange && hasTooManySeparators(input)

    private fun hasTooManySeparators(input: String) =
        input.count { it == Char.dot || it == Char.comma } > MAX_SEPARATOR_COUNT

    private fun hasTooManyDecimalNumbers(input: String) =
        hasTooManyDecimalNumbersWithSeparator(input, Char.dot)
                || hasTooManyDecimalNumbersWithSeparator(input, Char.comma)

    private fun hasTooManyDecimalNumbersWithSeparator(input: String, possibleSeparator: Char) =
        with(input) {
            contains(possibleSeparator)
                    && substringAfter(possibleSeparator).length > MAX_DECIMAL_NUMBERS_COUNT
        }

    private fun setTextAndSelection(text: String, selectionPosition: Int = text.length) {
        setText(text)
        setSelection(selectionPosition)
    }

    private fun onUserInputChanged(
        input: String,
        position: Int,
        changeCountBeforePosition: Int,
        changeCountAfterPosition: Int
    ) {
        when {
            hasWrongSeparator(input) -> applyProperSeparator(input)
            isSeparatorFirst(input) -> addZeroAsPrefix(input)
            isInputTooLong(input.formatToTwoDecimalPlacesString()) ->
                setProperTextLengthWithoutSeparator()
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
        val inputWithProperSeparator = when {
            input.contains(Char.dot) -> input.replace(Char.dot, separator)
            input.contains(Char.comma) -> input.replace(Char.comma, separator)
            else -> input
        }
        if (isSeparatorFirst(inputWithProperSeparator)) {
            addZeroAsPrefix(inputWithProperSeparator)
        } else {
            setTextAndSelection(inputWithProperSeparator)
        }
    }

    private fun isSeparatorFirst(input: String) =
        input.isNotEmpty() && input.first() == separator

    private fun addZeroAsPrefix(input: String) {
        val inputWithPrefix = "${Int.zero}$input"
        setTextAndSelection(inputWithPrefix)
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
                input,
                inputFormatted,
                position,
                changeCountBeforePosition,
                changeCountAfterPosition
            )
            restoreSelection(
                inputFormatted,
                input,
                position,
                changeCountBeforePosition,
                changeCountAfterPosition,
                lastSelectionStart,
                lastSelectionEnd
            )
        } else {
            onUserInput(input.toBigDecimal())
        }
    }

    private fun applyFormattedText(
        input: String,
        inputFormatted: String,
        position: Int,
        changeCountBeforePosition: Int,
        changeCountAfterPosition: Int
    ) {
        if (isSpaceDeleted(
                input,
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
        input: String,
        inputFormatted: String,
        position: Int,
        changeCountBeforePosition: Int,
        changeCountAfterPosition: Int
    ) =
        changeCountBeforePosition == ONE_CHANGE
                && changeCountAfterPosition == NO_CHANGES
                && inputFormatted.length > position
                && inputFormatted[position] == Char.nonBreakingSpace
                && input.count { it == Char.nonBreakingSpace } < inputFormatted.count { it == Char.nonBreakingSpace }

    private fun restoreSelection(
        inputFormatted: String,
        input: String,
        position: Int,
        changeCountBeforePosition: Int,
        changeCountAfterPosition: Int,
        lastSelectionStart: Int,
        lastSelectionEnd: Int
    ) {
        when {
            shouldSetSelectionOnEnd(input, inputFormatted, lastSelectionEnd) ->
                setSelection(inputFormatted.length)
            isSpaceDeleted(
                input,
                inputFormatted,
                position,
                changeCountBeforePosition,
                changeCountAfterPosition
            ) -> setSelection(lastSelectionStart - ONE_CHANGE)
            inputFormatted.length > input.length ->
                setSelection(lastSelectionStart + inputFormatted.length - input.length)
            hasInputMoreLengthThanInputFormattedAndSelectionIsNotOnSpace(
                input,
                inputFormatted,
                lastSelectionStart
            ) -> setSelection(lastSelectionStart - input.length + inputFormatted.length)
            else -> setSelection(lastSelectionStart)
        }
    }

    private fun hasInputMoreLengthThanInputFormattedAndSelectionIsNotOnSpace(
        input: String,
        inputFormatted: String,
        lastSelectionStart: Int
    ) =
        input.length > inputFormatted.length && input[lastSelectionStart] != Char.nonBreakingSpace

    private fun shouldSetSelectionOnEnd(
        input: String,
        inputFormatted: String,
        lastSelectionEnd: Int
    ) =
        lastSelectionEnd == input.length || lastSelectionEnd > inputFormatted.length

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
            setProperTextLengthWithoutSeparator()
            showKeyboard()
        } else {
            hideKeyboard()
        }
    }

    private fun setProperTextLengthWithoutSeparator() {
        val input = text.toString().formatToTwoDecimalPlacesString()
        if (isInputTooLong(input)) {
            setTextAndSelection(input.substringTo(MAX_LENGTH_WITHOUT_SEPARATOR))
        } else {
            setSelection(input.length)
        }
    }

    companion object {
        const val DEFAULT_STYLE_RES = 0
        const val ACCEPTED_CHARACTERS = "0123456789,."
        const val MAX_LENGTH_WITHOUT_SEPARATOR = 9
        const val MAX_LENGTH_WITH_SEPARATOR = 10
        const val MAX_SEPARATOR_COUNT = 1
        const val MAX_DECIMAL_NUMBERS_COUNT = 2
        const val NO_CHANGES = 0
        const val ONE_CHANGE = 1
    }
}