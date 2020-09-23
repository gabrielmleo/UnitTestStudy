package com.example.unitteststudy.finished

import com.example.unitteststudy.domain.Calculator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class CalculatorFinishedTest {

    val subject = Calculator()

    @Nested
    inner class `Given a Calculator to resolve sum operation` {

        @Test
        fun `When we send two integers Should return a correct result`() {
            //Preparation
            val aParcel = 2
            val bParcel = 3
            val expectedValue = 5

            //Execution
            val result = subject.sum(numA = aParcel, numB = bParcel)


            //Verification
            Assertions.assertEquals(expectedValue, result)

        }

    }

    @Nested
    inner class `Given a Calculator to resolve sub operation` {

        @Test
        fun `When we send two integers Should return a correct result`() {
            //Preparation
            val numAMock = 1
            val numBMock = 1
            val expectedResult = 0

            //Execution
            val result = subject.sub(numA = numAMock, numB = numBMock)

            //Verification
            Assertions.assertEquals(expectedResult, result)
        }
    }

    @Nested
    inner class `Given a Calculator to resolve div operation` {

        @Test
        fun `When we send two integers Should return a correct result`() {
            //Preparation
            val numAMock = 2
            val numBMock = 2
            val expected = 1

            //Execution
            val result = subject.div(numA = numAMock, numB = numBMock)

            //Verification
            Assertions.assertEquals(expected, result)
        }

        @Test
        fun `When we send zero as denominator Should return null`() {
            //Preparation
            val numAMock = 2
            val numBMock = 0
            val expected = null

            //Execution
            val result = subject.div(numA = numAMock, numB = numBMock)

            //Verification
            Assertions.assertEquals(expected, result)
        }
    }

    @Nested
    inner class `Given a Calculator to resolve mult operation` {

        @Test
        fun `When we send two integers Should return a correct result`() {
            //Preparation
            val numAMock = 2
            val numBMock = 2
            val expected = 4

            //Execution
            val result = subject.multi(numA = numAMock, numB = numBMock)

            //Verification
            Assertions.assertEquals(expected, result)
        }
    }
}