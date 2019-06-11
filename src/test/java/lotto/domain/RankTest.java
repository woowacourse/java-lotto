package lotto.domain;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertThrows;

public class RankTest {
    @Test
    void 예외확인() {
        assertThrows(IllegalArgumentException.class, () -> {
            Rank.valueOf(7, true);
        });
    }
}
