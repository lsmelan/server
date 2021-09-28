package com.nr.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {

    @Test
    void isValid_shouldReturnTrueIfTextIsA9DigitNumber() {
        Input input = new Input();
        assertTrue(input.isValid("000456789"));
    }

    @Test
    void isValid_shouldReturnTrueIfTextIsA9DigitNumberMultiLine() {
        Input input = new Input();
        assertTrue(input.isValid("000456789\n000456710"));
    }

    @Test
    void validateInput_shouldReturnTrueIfTextIsEqualToTerminate() {
        Input input = new Input();
        assertTrue(input.isValid("terminate"));
    }

    @Test
    void isValid_shouldReturnFalseIfTextIsInvalid() {
        Input input = new Input();
        assertFalse(input.isValid("AAAAAAAAA"));
    }

    @Test
    void isValid_shouldReturnFalseIfTextIsANon9DigitNumber() {
        Input input = new Input();
        assertFalse(input.isValid("12345"));
    }

    @Test
    void isValid_shouldReturnFalseIfTextIsANon9DigitNumberMultiLine() {
        Input input = new Input();
        assertFalse(input.isValid("000456789\n00045678"));
    }

    @Test
    void isValid_shouldReturnFalseIfTextIsEmpty() {
        Input input = new Input();
        assertFalse(input.isValid(""));
    }
}