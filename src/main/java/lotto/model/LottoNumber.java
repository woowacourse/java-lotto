package lotto.model;

import lotto.exception.OverRangeException;

import java.util.*;

public class LottoNumber {
    private static final int FIRST_LOTTO_NUMBER = 1;
    private static final int LAST_LOTTO_NUMBER = 45;
    private static final String OVER_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE = "1 ~ 45 범위를 벗어났습니다.";
    private static final Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();

    private int lottoNumber;

    static {
        for (int number = FIRST_LOTTO_NUMBER; number < LAST_LOTTO_NUMBER; number++) {
            lottoNumbers.put(number, new LottoNumber(number));
        }
    }

    private LottoNumber(int number) {
        this.lottoNumber = number;
    }

    private static void checkLottoNumberRange(int number) {
        if (number < FIRST_LOTTO_NUMBER || number > LAST_LOTTO_NUMBER) {
            throw new OverRangeException(OVER_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE);
        }
    }

    public static LottoNumber valueOf(int number) {
        checkLottoNumberRange(number);
        return lottoNumbers.get(number);
    }

    public static List<LottoNumber> allLottoNumbers() {
        List<LottoNumber> allLottoNumbers = new ArrayList<>();
        for (int number : lottoNumbers.keySet()) {
            allLottoNumbers.add(lottoNumbers.get(number));
        }
        return allLottoNumbers;
    }

    public static void sortLottoNumber(List<LottoNumber> lottoTicket) {
        lottoTicket.sort(Comparator.comparingInt(lottoNumber -> lottoNumber.lottoNumber));
    }


    public int getLottoNumber() {
        return lottoNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoNumber)) return false;
        LottoNumber that = (LottoNumber) o;
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }

}
