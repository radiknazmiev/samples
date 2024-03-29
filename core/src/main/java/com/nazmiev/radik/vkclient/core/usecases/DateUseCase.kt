package com.nazmiev.radik.vkclient.core.usecases

interface DateUseCase {

    fun getCurrentDateTime(): String

    fun getCurrentDate(): String

    fun getFutureDateTime(milliseconds: Int): String
}