package com.nazmiev.radik.vkclient.core.usecases

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class DateUseCaseImpl @Inject constructor(): DateUseCase {

    override fun getCurrentDateTime(): String {
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.US)
        val currentTime = Calendar.getInstance().time
        return simpleDateFormat.format(currentTime)
    }

    override fun getCurrentDate(): String {
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)
        val currentTime = Calendar.getInstance().time
        return simpleDateFormat.format(currentTime)
    }
}