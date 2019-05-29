package lotto.domain;

import lotto.Exception.InvalidLottoNumberException;

import java.util.*;

public class LottoNumber implements Comparable<LottoNumber>{
    private static final int LOTTO_SIZE = 6;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final List<LottoNumber> lottoNumbers = new ArrayList<>();
    private final int number;

    static {
        for (int i = MIN_LOTTO_NUMBER; i < MAX_LOTTO_NUMBER; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    private LottoNumber(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new InvalidLottoNumberException();
        }
        this.number = number;
    }

    public static List<LottoNumber> getAutoLottoNumbers() {
        Collections.shuffle(lottoNumbers);
        List<LottoNumber> autoLottoNumbers = new ArrayList<>();

        for (int i = 0; i < LOTTO_SIZE; i++) {
            autoLottoNumbers.add(lottoNumbers.get(i));
        }
        Collections.sort(autoLottoNumbers);
        return autoLottoNumbers;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return number-o.number;
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
