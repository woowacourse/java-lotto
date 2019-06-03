package lotto.model;

import java.util.*;

public class LottoNumber {
    private static final int MAXIMUM_LOTTO_NUMBER = 45;
    private static List<LottoNumber> lottoNumbers = new ArrayList<>();
    private int number;

    static {
        for (int i = 1; i < MAXIMUM_LOTTO_NUMBER + 1; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    public static List<LottoNumber> convertNumbersToLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> returnList = new ArrayList<>();
        for (int i = 0, n = numbers.size(); i < n; i++) {
            returnList.add(lottoNumbers.get(numbers.get(i) - 1));
        }
        return returnList;
    }

    public LottoNumber(int number) throws IllegalLottoNumberException {
        //todo 상수
        if (number < 1 || number > 45) {
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
