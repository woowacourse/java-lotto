package lotto.utils;

import org.junit.jupiter.api.Test;

import lotto.domain.exceptions.IllegalFormatException;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {
    @Test
    void check_valid_input() {
        assertThrows(IllegalFormatException.class, () -> {
            InputParser.parseLotto("1, 2, 3, 4, 5, 6");
        });
    }

    @Test
    void check_valid_a_number_input() {
        assertThrows(IllegalFormatException.class, () -> {
            InputParser.parseNumber("d");
        });
    }
}