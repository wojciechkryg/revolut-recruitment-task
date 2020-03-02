package com.wojdor.common.extension

fun <E> MutableList<E>.makeFirst(element: E) {
    remove(element)
    addFirst(element)
}

fun <E> MutableList<E>.addFirst(element: E) {
    add(0, element)
}