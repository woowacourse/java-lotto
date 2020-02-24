package lotto.domain;

import java.util.*;

import static lotto.domain.Lotto.LOTTO_NUMBERS_COUNT;
import static lotto.domain.LottoNumber.MAX_VALUE;
import static lotto.domain.LottoNumber.MIN_VALUE;

public class LottoFactory {
    private static final Map<Integer, LottoNumber> allLottoNumbers = createAllLottoNumbers();
    private static final int FROM_INDEX = 0;
    private static final int TO_INDEX = LOTTO_NUMBERS_COUNT;

    private static Map<Integer, LottoNumber> createAllLottoNumbers() {
        Map<Integer, LottoNumber> allLottoNumbers = new HashMap<>();
        for (int number = MIN_VALUE; number <= MAX_VALUE; number++) {
            allLottoNumbers.put(number, LottoNumber.of(number));
        }
        return allLottoNumbers;
    }

    public static List<LottoNumber> createLottoNumbers() {
        List<Integer> lottoNumbersIndex = shuffleAllLottoNumbersIndex();
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int index : lottoNumbersIndex) {
            lottoNumbers.add(allLottoNumbers.get(index));
        }
        return lottoNumbers;
    }

    private static List<Integer> shuffleAllLottoNumbersIndex() {
        List<Integer> allLottoNumbersIndex = new ArrayList<>(allLottoNumbers.keySet());
        Collections.shuffle(allLottoNumbersIndex);
        return allLottoNumbersIndex.subList(FROM_INDEX, TO_INDEX);
    }
}
