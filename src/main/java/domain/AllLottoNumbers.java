package domain;

import java.util.*;

public class AllLottoNumbers {
    private static final Map<Integer, LottoNumber> allLottoNumbers = new HashMap<>();
    private static final List<Integer> allLottoNumbersKeySet;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;

    static {
        for (int index = MIN_LOTTO_NUMBER; index <= MAX_LOTTO_NUMBER; index++) {
            allLottoNumbers.put(index, new LottoNumber(index));
        }
        allLottoNumbersKeySet = new ArrayList<>(allLottoNumbers.keySet());
    }

    public static List<Integer> getLottoNumbersKeySet() {
        return allLottoNumbersKeySet;
    }

    public static LottoNumber get(final int bonusNumber) {
        return allLottoNumbers.get(bonusNumber);
    }
}
