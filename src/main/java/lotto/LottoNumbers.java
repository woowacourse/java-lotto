package lotto;

import java.util.*;

public class LottoNumbers {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int NUMBER_OF_NUMBERS_IN_LOTTO = 6;

    private final Map<Integer, LottoNumber> numbers = new HashMap<>();

    public LottoNumbers() {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            numbers.put(i, new LottoNumber(i));
        }
    }

    public List<LottoNumber> getRandomLottoNumbers() {
        List<LottoNumber> numberList = new ArrayList<>(numbers.values());

        Collections.shuffle(numberList);
        return numberList.subList(0, NUMBER_OF_NUMBERS_IN_LOTTO);
    }
}
