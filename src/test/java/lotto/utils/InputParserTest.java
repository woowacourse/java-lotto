package lotto.utils;

import org.junit.jupiter.api.Test;

import lotto.exceptions.IllegalInputFormatException;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {
    @Test
    void check_valid_input() {
        assertThrows(IllegalInputFormatException.class, () -> {
            InputParser.parse("1, 2, 3, 4, 5, 6");
        });
    }
}