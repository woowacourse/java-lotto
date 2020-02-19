package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoFactory {

    public static final int LOTTO_SIZE = 6;

    public static List<LottoNumber> createOneLotto() {
        Set<LottoNumber> lotto = new HashSet<>();
        while (lotto.size() < LOTTO_SIZE) {
            int number = RandomNumberGenerator.generate();
            lotto.add(new LottoNumber(number));
        }
        return new ArrayList<LottoNumber>(lotto);
    }
}
