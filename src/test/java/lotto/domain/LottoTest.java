package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LottoTest {
    @Test
    void 생성() {
        final Lotto lotto = new Lotto();
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), lotto.numbers());
    }

    @Test
    void 생성2() {
        final Lotto lotto = new Lotto(Arrays.asList(1, 3, 5, 7, 9, 11));
        assertEquals(Arrays.asList(1, 3, 5, 7, 9, 11), lotto.numbers());
    }

    @Test
    void 비교() {
        Lotto lotto1 = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(lotto1, lotto2);
    }
}