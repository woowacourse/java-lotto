package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoGenerator {
    public static Lotto create() {
        List<LottoNumber> allLottoNumbers = LottoNumber.getLottoNumbers();
        Collections.shuffle(allLottoNumbers);

        List<LottoNumber> lottoNumbers = createLottoNumbers(allLottoNumbers);
        Collections.sort(lottoNumbers);

        return new Lotto(lottoNumbers);
    }

    private static List<LottoNumber> createLottoNumbers(List<LottoNumber> allLottoNumbers) {
        return allLottoNumbers
                .stream()
                .limit(Lotto.LOTTO_NUMBER_SIZE)
                .collect(Collectors.toList());
    }
}
