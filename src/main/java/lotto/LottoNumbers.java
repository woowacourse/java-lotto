package lotto;

import lotto.exception.InvalidLottoNumbersException;

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

    public List<LottoNumber> getLottoNumbers() {
        List<LottoNumber> numberList = new ArrayList<>(numbers.values());

        Collections.shuffle(numberList);
        return numberList.subList(0, NUMBER_OF_NUMBERS_IN_LOTTO);
    }

    public List<LottoNumber> getLottoNumbers(List<Integer> inputNumbers) {
        List<LottoNumber> numberList = new ArrayList<>();

        for (int number : inputNumbers) {
            checkNumberIn(number);
            numberList.add(numbers.get(number));
        }
        return numberList;
    }

    private void checkNumberIn(int number) {
        if (numbers.get(number) == null) {
            throw new InvalidLottoNumbersException("로또에 사용되는 숫자가 아닌 값이 포함되어 있습니다.");
        }
    }
}
