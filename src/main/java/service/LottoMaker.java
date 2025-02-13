package service;

import domain.Lotto;
import java.util.Collections;
import java.util.List;

public class LottoMaker {

    private LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

    public Lotto createLotto() {
        List<Integer> generate = lottoNumberGenerator.generateLottoNumbers();
        Collections.sort(generate);
        return new Lotto(generate);
    }
}
