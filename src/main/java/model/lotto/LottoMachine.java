package model.lotto;

import java.util.Collections;
import java.util.List;

public class LottoMachine {
    private final RandomNumberGenerator randomNumberGenerator;

    public LottoMachine(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public Lotto generateLotto() {
        List<Integer> generateNumber = randomNumberGenerator.generateLottoNumbers();
        Collections.sort(generateNumber);
        return new Lotto(generateNumber);
    }
}