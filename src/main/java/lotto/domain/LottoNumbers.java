package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {
    private static final int LOTTO_NUMBER_SIZE = 45;
    private static final List<LottoNumber> lottoNumbers = LottoNumberFactory.generateLottoNumbers();

    public static List<LottoNumber> getAutoLottoNumbers() {
        Collections.shuffle(lottoNumbers);
        List<LottoNumber> autoLottoNumbers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            autoLottoNumbers.add(lottoNumbers.get(i));
        }
        return autoLottoNumbers;
    }


    private boolean isDuplicateLottoNumbers(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream().distinct().collect(Collectors.toList()).size() != LOTTO_NUMBER_SIZE;
    }
}
