package lotto.model.winninglotto;

import lotto.model.lotto.BonusNumber;
import lotto.model.lotto.LottoNumber;
import lotto.model.lotto.LottoTicket;
import lotto.model.winninglotto.exception.InvalidWinningLottoException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    @Test
    void 보너스_번호가_당첨로또에_포함되어_있을_때_예외_발생() {
        List<LottoNumber> lottoNumbers = Arrays.asList(new LottoNumber(1)
                , new LottoNumber(2)
                , new LottoNumber(3)
                , new LottoNumber(4)
                , new LottoNumber(5)
                , new LottoNumber(45));
        assertThrows(InvalidWinningLottoException.class, () -> new WinningLotto(new LottoTicket(new TreeSet<>(lottoNumbers)), new BonusNumber(45)));
    }
}
