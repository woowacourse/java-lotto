package lotto.domain;

import java.util.List;
import lotto.domain.generator.LottoNumberGenerator;

public class LottoTicket {

    private final List<Integer> lottoNumbers;

    public LottoTicket(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumbers = lottoNumberGenerator.generate();
    }

}
