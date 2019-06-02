package lotto.utils;

import org.junit.jupiter.api.Test;

import lotto.exceptions.IllegalFormatException;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {
    @Test
    void check_valid_input_start_with_comma() {
        assertThrows(IllegalFormatException.class, () -> {
            InputParser.parseLotto(",1, 2, 3, 4, 5, 6");
        });
    }

    @Test
    void check_valid_input_end_with_comma() {
        assertThrows(IllegalFormatException.class, () -> {
            InputParser.parseLotto("1, 2, 3, 4, 5, 6,");
        });
    }

    @Test
    void check_valid_a_number_input() {
        assertThrows(IllegalFormatException.class, () -> {
            InputParser.parseNumber("d");
        });
    }

    @Test
    void check_valid_number_range() {
        assertThrows(IllegalFormatException.class, () -> {
            InputParser.parseNumber("46,47,48,49,50,51");
        });
    }
}