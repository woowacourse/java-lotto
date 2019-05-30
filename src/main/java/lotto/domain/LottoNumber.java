package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class LottoNumber implements Comparable<LottoNumber>{
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
        this.number = number;
    }

    public static List<LottoNumber> getAutoLottoNumbers() {
        Collections.shuffle(lottoNumbers);
        List<LottoNumber> autoLottoNumbers = lottoNumbers.stream()
                .collect(Collectors.toList())
                .subList(0,6);
        Collections.sort(autoLottoNumbers);
        return autoLottoNumbers;
    }

    public static boolean isNotValidLottoNumber(int number) {
         return number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER;
    }

    public static List<LottoNumber> getLotto(List<Integer> collect) {
        List<LottoNumber> lotto =lottoNumbers.stream()
                .filter(n -> collect.contains(n.number))
                .collect(Collectors.toList());
        Collections.sort(lotto);
        return lotto;
    }

    public static LottoNumber getLottoNumber(int lottoNumber){
        return new LottoNumber(lottoNumber);
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
