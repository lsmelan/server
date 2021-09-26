package com.nr.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {

    @Test
    void validateInput_shouldNotThrowExceptionIfTextIsA9DigitNumber() throws Exception {
        Input input = new Input();
        input.validate("000456789");
    }

    @Test
    void validateInput_shouldNotThrowExceptionIfTextIsA9DigitNumberMultiLine() throws Exception {
        Input input = new Input();
        input.validate("000456789\n000456710");
    }

    @Test
    void validateInput_shouldNotThrowExceptionIfTextIsEqualToTerminate() throws Exception {
        Input input = new Input();
        input.validate("terminate");
    }

    @Test
    void validateInput_shouldThrowExceptionIfTextIsInvalid() {
        Input input = new Input();
        assertThrows(Exception.class,
                ()-> input.validate("terminat"));
    }

    @Test
    void validateInput_shouldThrowExceptionIfTextIsANon9DigitNumber() {
        Input input = new Input();
        assertThrows(Exception.class,
                ()-> input.validate("12345"));
    }

    @Test
    void validateInput_shouldThrowExceptionIfTextIsANon9DigitNumberMultiLine() {
        Input input = new Input();
        assertThrows(Exception.class,
                ()-> input.validate("000456789\n00045678"));
    }

    @Test
    void validateInput_shouldThrowExceptionIfTextIsEmpty() {
        Input input = new Input();
        assertThrows(Exception.class,
                ()-> input.validate(""));
    }
}