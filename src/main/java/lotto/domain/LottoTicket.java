package lotto.domain;

import java.util.Collections;
import java.util.List;
import lotto.domain.generator.LottoNumberGenerator;
import lotto.domain.vo.LottoNumber;

public class LottoTicket {

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumbers = lottoNumberGenerator.generate();
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    public boolean isSame(WinningNumber winningNumber) {
        return lottoNumbers.contains(winningNumber.getWinningNumber());
    }
}
