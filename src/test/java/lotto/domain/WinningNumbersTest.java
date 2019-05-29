package lotto.domain;

import org.junit.jupiter.api.Test;

import lotto.domain.exceptions.IllegalFormatException;
import lotto.domain.lotto.Number;

import static org.junit.jupiter.api.Assertions.*;

class WinningNumbersTest {
    @Test
    void WinningNumbers_format_exception_less_numbers() {
        assertThrows(IllegalFormatException.class, () -> {
            new WinningNumbers("1");
        });
    }

    @Test
    void WinningNumbers_format_exception_more_numbers() {
        assertThrows(IllegalFormatException.class, () -> {
            new WinningNumbers("1, 2, 3, 4, 5, 6, 7");
        });
    }

    @Test
    void WinningNumbers_format_exception_delimiter() {
        assertThrows(NumberFormatException.class, () -> {
            new WinningNumbers("1 - 2 - 3, 4, 5, 6, 7");
        });
    }

    @Test
    void WinningNumbers_contains() {
        assertTrue(new WinningNumbers("1, 2, 3, 4, 5, 6").contains(Number.of(1)));
    }

    @Test
    void WinningNumbers_not_contains() {
        assertFalse(new WinningNumbers("1, 2, 3, 4, 5, 6").contains(Number.of(7)));
    }
}