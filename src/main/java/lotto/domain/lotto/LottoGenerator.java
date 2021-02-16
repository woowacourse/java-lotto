package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.number.LottoNumber;

public class LottoGenerator {

    private static final int LOTTO_NUMBER_MINIMUM = 1;
    private static final int LOTTO_NUMBER_MAXIMUM = 45;
    private static final int LOTTO_COUNT_MAXIMUM = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoGenerator() {
        lottoNumbers = new ArrayList<>();

        for (int i = LOTTO_NUMBER_MINIMUM; i <= LOTTO_NUMBER_MAXIMUM; i++) {
            lottoNumbers.add(
                new LottoNumber(i)
            );
        }
    }

    public LottoGroup generateLotties(int count) {
        List<LottoNumbers> lotties = new ArrayList();

        for (int i = 0; i < count; i++) {
            LottoNumbers lottoNumbers = new LottoNumbers(generateRandomLottoNumber());
            lotties.add(lottoNumbers);
        }

        return new LottoGroup(lotties);
    }

    private List<LottoNumber> generateRandomLottoNumber() {
        Collections.shuffle(lottoNumbers);

        return new ArrayList<>(lottoNumbers.subList(0, LOTTO_COUNT_MAXIMUM));
    }
}
