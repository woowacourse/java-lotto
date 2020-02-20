package domain;

import java.util.HashSet;
import java.util.Set;

public class LottoFactory {

    public static final int LOTTO_SIZE = 6;

    public static Lotto createOneLotto() {
        Set<LottoNumber> lotto = new HashSet<>();
        while (lotto.size() < LOTTO_SIZE) {
            int number = RandomNumberGenerator.generate();
            lotto.add(new LottoNumber(number));
        }
        return new Lotto(lotto);
    }
}
