package lotto.domain;

import lotto.dto.WinningLottoDto;
import lotto.domain.TicketModel.Ticket;
import lotto.domain.TicketModel.LottoGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WinningLottoTest {
    private WinningLotto winningLotto;
    private final LottoGenerator generator = new LottoGenerator();

    @BeforeEach
    void setUp() {
        winningLotto = new WinningLotto(new WinningLottoDto("1,2,3,4,5,6", "7"));
    }

    @Test
    void 매칭() {
        Ticket lotto = generator.create(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(6, winningLotto.match(lotto));
        assertFalse(winningLotto.bonus(lotto));
    }

    @Test
    void 매칭2() {
        Ticket lotto = generator.create(Arrays.asList(1, 2, 3, 4, 5, 7));
        assertEquals(5, winningLotto.match(lotto));
        assertTrue(winningLotto.bonus(lotto));
    }

    @Test
    void 매칭3() {
        Ticket lotto = generator.create(Arrays.asList(1, 2, 3, 4, 9, 8));
        assertEquals(4, winningLotto.match(lotto));
        assertFalse(winningLotto.bonus(lotto));
    }

    @Test
    void 매칭4() {
        Ticket lotto = generator.create(Arrays.asList(1, 2, 3, 10, 9, 8));
        assertEquals(3, winningLotto.match(lotto));
        assertFalse(winningLotto.bonus(lotto));
    }

    @Test
    void 매칭5() {
        Ticket lotto = generator.create(Arrays.asList(1, 2, 11, 10, 9, 8));
        assertEquals(2, winningLotto.match(lotto));
        assertFalse(winningLotto.bonus(lotto));
    }

    @Test
    void 매칭6() {
        Ticket lotto = generator.create(Arrays.asList(1, 12, 11, 10, 9, 8));
        assertEquals(1, winningLotto.match(lotto));
        assertFalse(winningLotto.bonus(lotto));
    }
}