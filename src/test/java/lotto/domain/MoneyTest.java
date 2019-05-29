package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {
    @Test
    void Money_생성() {
        assertThrows(IllegalArgumentException.class, () -> {
           new Money(999);
        });
    }
}