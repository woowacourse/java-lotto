package lotto.domain;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.List;
import lotto.domain.generator.LottoNumberGenerator;

public class LottoTicket {

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumbers = lottoNumberGenerator.generate()
                .stream()
                .map(LottoNumber::new)
                .collect(toList());
    }

    public boolean isSame(WinningNumber winningNumber) {
        return lottoNumbers.contains(winningNumber.getWinningNumber());
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
