package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumbersGenerator {
    private static final List<LottoNumber> lottoNumbers = LottoNumberBox.get();
    static final int LOTTO_NUMBER_SIZE = 6;
    private static final int ONE = 1;

    static List<List<LottoNumber>> generate(int lottosSize) {
        List<List<LottoNumber>> lottoNumbers = new ArrayList<>(new ArrayList<>());
        for (int i = 0; i < lottosSize; i++) {
            List<LottoNumber> lottoNumber = generateLottoNumbers();
            if (!lottoNumbers.contains(lottoNumber)) {
                lottoNumbers.add(lottoNumber);
            }
        }
        return lottoNumbers;
    }

    private static List<LottoNumber> generateLottoNumbers() {
        Collections.shuffle(lottoNumbers);
        return new ArrayList<>(
            Collections.unmodifiableCollection(lottoNumbers.subList(ONE, ONE + LOTTO_NUMBER_SIZE)));
    }

    private static boolean isDuplicated(List<LottoNumber> lottoNumbers, int lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

}
