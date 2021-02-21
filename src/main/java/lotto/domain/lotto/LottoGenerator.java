package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import lotto.domain.number.LottoNumber;
import lotto.domain.number.Number;
import lotto.domain.number.PayOut;

import static java.util.stream.Collectors.toList;

public class LottoGenerator {

    private static final int LOTTO_NUMBER_MINIMUM = 1;
    private static final int LOTTO_NUMBER_MAXIMUM = 45;
    private static final int LOTTO_COUNT_MAXIMUM = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoGenerator() {
        lottoNumbers = new ArrayList<>();

        for (int i = LOTTO_NUMBER_MINIMUM; i <= LOTTO_NUMBER_MAXIMUM; i++) {
            lottoNumbers.add(
                    new LottoNumber(new Number(i))
            );
        }
    }

    private LottoGroup generateLottos(int count) {
        return new LottoGroup(
                Stream.generate(this::generateRandomLottoNumber)
                        .limit(count)
                        .map(LottoNumbers::new)
                        .collect(toList())
        );
    }

    public LottoGroup generateLottosWithManualLottoNumbers(List<String> manualLottoNumbers, PayOut payOut) {
        return new LottoGroup(
                manualLottoNumbers.stream()
                        .map(LottoNumbers::valueOf)
                        .collect(toList())
        ).add(
                generateLottos(payOut.getGameCount())
        );
    }

    private List<LottoNumber> generateRandomLottoNumber() {
        Collections.shuffle(lottoNumbers);

        return lottoNumbers.stream()
                .limit(LOTTO_COUNT_MAXIMUM)
                .collect(toList());
    }
}
