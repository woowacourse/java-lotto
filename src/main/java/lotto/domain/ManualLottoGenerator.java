package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManualLottoGenerator {
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;

    private static List<LottoNumber> allLottoNumbers;

    static {
        allLottoNumbers = new ArrayList<>();
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            allLottoNumbers.add(new LottoNumber(i));
        }
    }

    public static Lotto generate(List<String> lottoNumbersInput) {
        List<LottoNumber> lottoNumbers = makeLottoNumbers(lottoNumbersInput);
        return new Lotto(lottoNumbers);
    }

    private static List<LottoNumber> makeLottoNumbers(List<String> lottoNumbersInput) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (String s : lottoNumbersInput) {
            int lottoNumber = Integer.parseInt(s);
            validateLottoNumber(lottoNumber);
            lottoNumbers.add(allLottoNumbers.get(lottoNumber - 1));
        }
        Collections.sort(lottoNumbers);

        return lottoNumbers;
    }

    private static void validateLottoNumber(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또 숫자는 1 ~ 45만 가능합니다.");
        }
    }
}
