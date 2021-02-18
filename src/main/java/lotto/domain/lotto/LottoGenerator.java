package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.number.LottoNumber;

public class LottoGenerator {

    private static final LottoGenerator LOTTO_GENERATOR = new LottoGenerator();
    private static final int LOTTO_NUMBER_MINIMUM = 1;
    private static final int LOTTO_NUMBER_MAXIMUM = 45;
    private static final int LOTTO_COUNT_MAXIMUM = 6;

    private final List<LottoNumber> lottoNumbers;

    private LottoGenerator() {
        lottoNumbers = new ArrayList<>();

        for (int i = LOTTO_NUMBER_MINIMUM; i <= LOTTO_NUMBER_MAXIMUM; i++) {
            lottoNumbers.add(LottoNumber.valueOf(i));
        }
    }

    public static LottoGenerator getInstance() {
        return LOTTO_GENERATOR;
    }

    public LottoGroup generateLotties(int count) {
        List<LottoNumbers> lottos = new ArrayList();

        for (int i = 0; i < count; i++) {
            LottoNumbers lottoNumbers = new LottoNumbers(generateRandomLottoNumber());
            lottos.add(lottoNumbers);
        }

        return new LottoGroup(lottos);
    }

    private List<LottoNumber> generateRandomLottoNumber() {
        Collections.shuffle(lottoNumbers);

        return new ArrayList<>(lottoNumbers.subList(0, LOTTO_COUNT_MAXIMUM))
            .stream()
            .sorted(Comparator.comparingInt(LottoNumber::toInt))
            .collect(Collectors.toList());
    }
}
