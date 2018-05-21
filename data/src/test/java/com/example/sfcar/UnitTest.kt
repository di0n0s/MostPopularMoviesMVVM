package com.example.sfcar

import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
open class UnitTest {

    @Suppress("LeakingThis")
    @Rule
    @JvmField
    val injectMocks = InjectMocksRule.create(this@UnitTest)
}