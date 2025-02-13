package service;

import domain.Lotto;
import java.util.Collections;
import java.util.List;

public class LottoMaker {

    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoMaker(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public Lotto createLotto() {
        List<Integer> generate = lottoNumberGenerator.generateLottoNumbers();
        Collections.sort(generate);
        return new Lotto(generate);
    }
}
