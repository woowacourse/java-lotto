package lotto.domain.TicketModel;

import lotto.domain.Dto.WinningLottoDto;
import lotto.domain.Exceptions.LottoNumberException;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LottoGeneratorTest {

    @Test
    void 로또숫자아님() {
        assertThrows(LottoNumberException.class, () -> {
                    new LottoGenerator().create(Arrays.asList(0, 1, 2, 3, 4, 5));
                }
        );
    }

    @Test
    void 중복숫자() {
        assertThrows(LottoNumberException.class, () -> {
            new LottoGenerator().create(Arrays.asList(1, 1, 2, 3, 4, 5));
        });
    }

    @Test
    void 동등로또() {
        LottoGenerator generator = new LottoGenerator();
        LottoNumbers numbers = new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(new Lotto(numbers), generator.create(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void name() {
        WinningLotto winningLotto = new WinningLotto(new WinningLottoDto("1,2,3,4,5,6", "7"));
        Ticket lotto = new LottoGenerator().create(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(6, winningLotto.match(lotto));
        assertFalse(winningLotto.bonus(lotto));
        assertEquals(Rank.FIRST, Rank.rank(winningLotto.match(lotto), winningLotto.bonus(lotto)));
    }
}