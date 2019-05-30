package lotto.domain;

import java.util.*;

public class LottoNumbersGenerator {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int SUBLIST_LOWER_BOUND = 0;
    private static final int SUBLIST_UPPER_BOUND = 6;
    private static final List<LottoNumber> totalLottoNumbers = new ArrayList<>();
    private static final List<LottoNumber> fixedTotalLottoNumbers = new ArrayList<>();

    static {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            LottoNumber lottoNumber = new LottoNumber(i);
            totalLottoNumbers.add(lottoNumber);
            fixedTotalLottoNumbers.add(lottoNumber);
        }
    }

    public static LottoNumber getLottoNumber(int number) {
        checkValidLottoNumber(number);
        return fixedTotalLottoNumbers.get(number - 1);
    }

    public static LottoNumbers getLottoNumbers() {
        Collections.shuffle(totalLottoNumbers, new Random());
        List<LottoNumber> lottoNumbers = new ArrayList<>(
                totalLottoNumbers.subList(SUBLIST_LOWER_BOUND, SUBLIST_UPPER_BOUND));
        return new LottoNumbers(lottoNumbers);
    }

    public static LottoNumbers getLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int number : numbers) {
            lottoNumbers.add(getLottoNumber(number));
        }
        return new LottoNumbers(lottoNumbers);
    }

    private static void checkValidLottoNumber(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또 숫자의 범위에서 벗어났습니다.");
        }
    }
}
