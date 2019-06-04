package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LottoTicketTest {
    @Test
    void 생성() {
        final Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
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

    @Test
    void 생성_중복() {
        assertThrows(IllegalArgumentException.class, () -> new Lotto(Arrays.asList(1, 1, 2, 3, 4, 5)));
    }

    @Test
    void 생성_모자람() {
        assertThrows(IllegalArgumentException.class, () -> new Lotto(Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test
    void 생성_범위() {
        assertThrows(IllegalArgumentException.class, () -> new Lotto(Arrays.asList(0, 1, 2, 3, 4, 5)));
    }

    @Test
    void 생성_범위2() {
        assertThrows(IllegalArgumentException.class, () -> new Lotto(Arrays.asList(46, 1, 2, 3, 4, 5)));
    }
}