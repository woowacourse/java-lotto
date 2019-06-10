package lotto.domain.TicketModel;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumberManagerTest {

    @Test
    void autoNumber() {
        for (int i = 0; i < 100; i++) {
            assertNotEquals(LottoNumberManager.autoNumber(), LottoNumberManager.autoNumber());
        }
    }

    @Test
    void 매뉴얼() {
        assertTrue(() -> LottoNumberManager.check(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void 체크_중복() {
        assertFalse(() -> LottoNumberManager.check(Arrays.asList(1, 1, 2, 3, 4, 5)));
    }

    @Test
    void 체크_범위초과() {
        assertFalse(() -> LottoNumberManager.check(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
    }

    @Test
    void 체크_숫자범위초과() {
        assertFalse(() -> LottoNumberManager.check(Arrays.asList(0, 1, 2, 3, 4, 5)));
    }

    @Test
    void 체크_숫자범위초과_2() {
        assertFalse(() -> LottoNumberManager.check(Arrays.asList(1, 2, 3, 4, 5, 50)));
    }

}