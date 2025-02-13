package service;

import java.util.Collections;
import java.util.List;
import model.lotto.Lotto;
import model.lotto.RandomNumberGenerator;

public class LottoMaker {
    private final RandomNumberGenerator randomNumberGenerator;

    public LottoMaker(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public Lotto generateLotto() {
        List<Integer> generateNumber = randomNumberGenerator.generateLottoNumbers();
        Collections.sort(generateNumber);
        return new Lotto(generateNumber);
    }
}