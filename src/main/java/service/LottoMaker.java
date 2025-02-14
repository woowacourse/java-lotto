package service;

import domain.Lotto;
import java.util.Collections;
import java.util.List;

public class LottoMaker {

    private static LottoMaker instance;

    private final LottoNumberGenerator lottoNumberGenerator;

    private LottoMaker(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public static LottoMaker getInstance() {
        if (instance == null) {
            instance = new LottoMaker(LottoNumberGenerator.getInstance());
        }
        return instance;
    }

    public Lotto createLotto() {
        List<Integer> generate = lottoNumberGenerator.generateLottoNumbers();
        Collections.sort(generate);
        return new Lotto(generate);
    }
}
