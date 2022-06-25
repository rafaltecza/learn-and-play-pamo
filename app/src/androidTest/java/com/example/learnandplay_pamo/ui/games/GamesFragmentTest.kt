package com.example.learnandplay_pamo.ui.games

import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

// Class created for Unit Tests
class GamesFragmentTest : TestCase()
{
    lateinit var gamesFragment : GamesFragment

    private var firstValue = 6 // First Value To Test mathematics operation
    private var secondValue = 2 // Second Value To Test mathematics operation

    private var additionResult = firstValue + secondValue // Addition Expected result
    private var subtractionResult = firstValue - secondValue // Subtraction Expected result
    private var multiplicationResult = firstValue * secondValue // Multiplication Expected result
    private var divisionResult = firstValue / secondValue // Division Expected result

    @Before
    override fun setUp()
    {
        gamesFragment = GamesFragment()
    }

    @Test
    fun testAddition() // Test for addition operation
    {
        gameType="addition"
        val result = gamesFragment.getResult(firstValue,secondValue)
        assertEquals(additionResult, result)
    }

    @Test
    fun testSubtraction() // Test for subtraction operation
    {
        gameType="subtraction"

        val result = gamesFragment.getResult(firstValue,secondValue)
        assertEquals(subtractionResult, result)
    }

    @Test
    fun testMultiplication() // Test for multiplication operation
    {
        gameType="multiplication"

        val result = gamesFragment.getResult(firstValue,secondValue)
        assertEquals(multiplicationResult, result)
    }

    @Test
    fun testDivision() // Test for division operation
    {
        gameType="division"

        val result = gamesFragment.getResult(firstValue,secondValue)
        assertEquals(divisionResult, result)
    }

    @Test
    fun testInvalidGameType() // Test for invalid type operation
    {
        gameType="other"
        val result = gamesFragment.getResult(firstValue,secondValue)
        assertEquals(0, result)
    }
}

