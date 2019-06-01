package lotto.model.winninglotto;

import lotto.model.lotto.BonusNumber;
import lotto.model.lotto.LottoNumber;
import lotto.model.lotto.LottoNumberRepository;
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
        List<LottoNumber> lottoNumbers = Arrays.asList(LottoNumberRepository.fromNumber(1)
                , LottoNumberRepository.fromNumber(2)
                , LottoNumberRepository.fromNumber(3)
                , LottoNumberRepository.fromNumber(4)
                , LottoNumberRepository.fromNumber(5)
                , LottoNumberRepository.fromNumber(45));
        assertThrows(InvalidWinningLottoException.class, () -> WinningLotto.of(LottoTicket.from(new TreeSet<>(lottoNumbers)), new BonusNumber(45)));
    }
}
