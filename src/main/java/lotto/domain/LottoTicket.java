package lotto.domain;

import java.util.Collections;
import java.util.List;
import lotto.domain.generator.LottoNumberGenerator;

public class LottoTicket {

    private final List<Integer> lottoNumbers;

    public LottoTicket(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumbers = lottoNumberGenerator.generate();
    }

    public List<Integer> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    public boolean isSame(WinningNumber winningNumber) {
        return lottoNumbers.contains(winningNumber.getWinningNumber());
    }
}
