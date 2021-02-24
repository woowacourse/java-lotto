package lotto.domain;

import java.util.*;

public class LottoGenerator {

    private static final int MAXIMUM_NUMBER = 45;
    private static final int LOTTO_NUMBER_LIMIT = 6;
    private final Set<LottoNumber> lottoNumbers = new HashSet<>();

    public LottoGenerator() {
        for (int i = 1; i <= MAXIMUM_NUMBER; i++) {
            lottoNumbers.add(LottoNumber.of(i));
        }
    }

    public Set<LottoNumber> generateLotto() {
        List<LottoNumber> lottoNumber = new ArrayList<>(lottoNumbers);
        Collections.shuffle(lottoNumber);
        return new HashSet<>(lottoNumber.subList(0, LOTTO_NUMBER_LIMIT));
    }
}
