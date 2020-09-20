package com.example.unitteststudy.finished

import com.example.unitteststudy.domain.Calculator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class CalculatorTest {

    @Nested
    inner class `Given a` {

        @Test
        fun `Should return`() {
            //Preparation
            val subject = Calculator()
            val aParcel = 2
            val bParcel = 3
            val expectedValue = 6

            //Execution
            val result = subject.sum(aParcel = aParcel, bParcel = bParcel)


            //Verification
            Assertions.assertEquals(expectedValue, result)

        }
    }
}