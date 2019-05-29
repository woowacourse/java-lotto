package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BudgetTest {
    @Test
    void 음수를_입력했을경우() {
        assertThrows(IllegalArgumentException.class, () -> new Budget(-1000));
    }
}
