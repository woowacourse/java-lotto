package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WinningLottoTest {
    private WinningLotto winningTicket;

    @BeforeEach
    void setUp() {
        winningTicket = LottoCreator.getLottoCreator().createWinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
    }

    @Test
    void 매칭() {
        Ticket lotto = LottoCreator.getLottoCreator().create(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(6, winningTicket.match(lotto));
        assertFalse(winningTicket.bonus(lotto));
    }

    @Test
    void 매칭2() {
        Ticket lotto = LottoCreator.getLottoCreator().create(Arrays.asList(1, 2, 3, 4, 5, 7));
        assertEquals(5, winningTicket.match(lotto));
        assertTrue(winningTicket.bonus(lotto));
    }

    @Test
    void 매칭3() {
        Ticket lotto = LottoCreator.getLottoCreator().create(Arrays.asList(1, 2, 3, 4, 9, 8));
        assertEquals(4, winningTicket.match(lotto));
        assertFalse(winningTicket.bonus(lotto));
    }

    @Test
    void 매칭4() {
        Ticket lotto = LottoCreator.getLottoCreator().create(Arrays.asList(1, 2, 3, 10, 9, 8));
        assertEquals(3, winningTicket.match(lotto));
        assertFalse(winningTicket.bonus(lotto));
    }

    @Test
    void 매칭5() {
        Ticket lotto = LottoCreator.getLottoCreator().create(Arrays.asList(1, 2, 11, 10, 9, 8));
        assertEquals(2, winningTicket.match(lotto));
        assertFalse(winningTicket.bonus(lotto));
    }

    @Test
    void 매칭6() {
        Ticket lotto = LottoCreator.getLottoCreator().create(Arrays.asList(1, 12, 11, 10, 9, 8));
        assertEquals(1, winningTicket.match(lotto));
        assertFalse(winningTicket.bonus(lotto));
    }
}