package lotto.domain.TicketModel;

import lotto.domain.Exceptions.LottoNumberException;
import lotto.domain.Rank;
import lotto.dto.WinningLottoDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LottoCreatorTest {

    @Test
    void 로또숫자아님() {
        assertThrows(LottoNumberException.class, () -> {
                    new LottoCreator().create(Arrays.asList(0, 1, 2, 3, 4, 5));
                }
        );
    }

    @Test
    void 중복숫자() {
        assertThrows(LottoNumberException.class, () -> {
            new LottoCreator().create(Arrays.asList(1, 1, 2, 3, 4, 5));
        });
    }

    @Test
    void 동등로또() {
        LottoCreator generator = new LottoCreator();
        LottoNumbers numbers = new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(new Lotto(numbers), generator.create(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void 매칭확인() {
        WinningTicket winningLotto = new WinningLotto(new WinningLottoDto("1,2,3,4,5,6", "7"), new LottoCreator());
        Ticket lotto = new LottoCreator().create(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(6, winningLotto.match(lotto));
        assertFalse(winningLotto.bonus(lotto));
        assertEquals(Rank.FIRST, Rank.rank(winningLotto.match(lotto), winningLotto.bonus(lotto)));
    }
}