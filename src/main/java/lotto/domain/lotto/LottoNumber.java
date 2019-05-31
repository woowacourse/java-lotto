package lotto.domain.lotto;

import java.util.*;

public class LottoNumber {
    public static final int MAXIMUM_NUMBER = 45;
    public static final int MINIMUM_NUMBER = 1;
    private static Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();
    private int number;

    static {
        for (int i = 1; i <= MAXIMUM_NUMBER; i++) {
            lottoNumbers.put(i, new LottoNumber(i));
        }
    }

    public static List<LottoNumber> convertToLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> returnList = new ArrayList<>();
        for (int i = 0, n = numbers.size(); i < n; i++) {
            returnList.add(lottoNumbers.get(numbers.get(i)));
        }
        return returnList;
    }

    public LottoNumber(int number) throws IllegalLottoNumberException {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new IllegalLottoNumberException();
        }
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
