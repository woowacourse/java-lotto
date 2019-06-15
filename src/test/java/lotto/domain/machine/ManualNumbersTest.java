package lotto.domain.machine;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ManualNumbersTest {
    @Test
    public void 수동_번호_갯수_예외() {
        assertThrows(IllegalArgumentException.class, () -> {
            ManualNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        });
    }
}