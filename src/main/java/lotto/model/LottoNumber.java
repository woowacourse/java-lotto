package lotto.model;

import lotto.model.exceptions.IllegalLottoNumberException;

import java.util.*;

public class LottoNumber {
    public static final int MAXIMUM_LOTTO_NUMBER = 45;
    public static final int MINMUM_LOTTO_NUMBERS = 1;
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
        if (number < MINMUM_LOTTO_NUMBERS || number > MAXIMUM_LOTTO_NUMBER) {
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
