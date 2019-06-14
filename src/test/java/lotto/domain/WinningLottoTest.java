package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WinningLottoTest {
    private final LottoCreator generator = new LottoCreator();
    private WinningTicket winningTicket;

    @BeforeEach
    void setUp() {
        Ticket ticket = new LottoCreator().create(Arrays.asList(1, 2, 3, 4, 5, 6));
        TicketNumber bonus = new LottoNumber(7);
        winningTicket = new WinningLotto(ticket, bonus);
    }

    @Test
    void 매칭() {
        Ticket lotto = generator.create(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(6, winningTicket.match(lotto));
        assertFalse(winningTicket.bonus(lotto));
    }

    @Test
    void 매칭2() {
        Ticket lotto = generator.create(Arrays.asList(1, 2, 3, 4, 5, 7));
        assertEquals(5, winningTicket.match(lotto));
        assertTrue(winningTicket.bonus(lotto));
    }

    @Test
    void 매칭3() {
        Ticket lotto = generator.create(Arrays.asList(1, 2, 3, 4, 9, 8));
        assertEquals(4, winningTicket.match(lotto));
        assertFalse(winningTicket.bonus(lotto));
    }

    @Test
    void 매칭4() {
        Ticket lotto = generator.create(Arrays.asList(1, 2, 3, 10, 9, 8));
        assertEquals(3, winningTicket.match(lotto));
        assertFalse(winningTicket.bonus(lotto));
    }

    @Test
    void 매칭5() {
        Ticket lotto = generator.create(Arrays.asList(1, 2, 11, 10, 9, 8));
        assertEquals(2, winningTicket.match(lotto));
        assertFalse(winningTicket.bonus(lotto));
    }

    @Test
    void 매칭6() {
        Ticket lotto = generator.create(Arrays.asList(1, 12, 11, 10, 9, 8));
        assertEquals(1, winningTicket.match(lotto));
        assertFalse(winningTicket.bonus(lotto));
    }
}