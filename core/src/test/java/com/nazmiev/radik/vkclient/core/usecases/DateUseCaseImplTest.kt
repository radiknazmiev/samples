package com.nazmiev.radik.vkclient.core.usecases

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class DateUseCaseImplTest {

    lateinit var dateUseCaseImpl: DateUseCaseImpl

    @Before
    fun setUp() {
        dateUseCaseImpl = DateUseCaseImpl()
    }

    @Test
    fun getCurrentDateTime() {
    }

    @Test
    fun getCurrentDate() {
    }

    @Test
    fun getFutureDateTime() {
        val time = dateUseCaseImpl.getFutureDateTime(1000)
    }
}