package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {

    private static final int MAXIMUM_NUMBER = 45;
    private static final int LOTTO_NUMBER_LIMIT = 6;
    private final List<Integer> lottoNumbers = new ArrayList<>();

    public LottoGenerator() {
        for (int i = 1; i <= MAXIMUM_NUMBER; i++) {
            lottoNumbers.add(i);
        }
    }

    public List<LottoNumber> generateLotto() {
        List<LottoNumber> lottoNumber = new ArrayList<>();
        for (int number : generateLottoNumbers()) {
            lottoNumber.add(new LottoNumber(number));
        }
        return lottoNumber;
    }

    public List<Integer> generateLottoNumbers() {
        Collections.shuffle(lottoNumbers);
        List<Integer> generatedLotto = lottoNumbers.subList(0, LOTTO_NUMBER_LIMIT);
        Collections.sort(generatedLotto);
        return generatedLotto;
    }
}
