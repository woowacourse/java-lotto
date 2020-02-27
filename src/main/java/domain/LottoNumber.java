package domain;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber implements Comparable {
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final Map<Integer, LottoNumber> lottoNumber = new HashMap<>();

    private int number;
    static {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            lottoNumber.put(i, new LottoNumber(i));
        }
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(int number) {
        checkLottoRange(number);
        return lottoNumber.get(number);
    }

    public static LottoNumber valueOf(String number) {
        number = number.trim();
        checkNotNumber(number);
        int numberIntegerValue = Integer.parseInt(number);
        checkLottoRange(numberIntegerValue);
        return lottoNumber.get(numberIntegerValue);
    }

    private static void checkLottoRange(int number) {
        if (isNotLottoNumber(number)) {
            throw new IllegalArgumentException("로또 숫자 범위를 넘어섰습니다.");
        }
    }

    private static boolean isNotLottoNumber(int number) {
        return number > MAX_LOTTO_NUMBER || number < MIN_LOTTO_NUMBER;
    }

    private static void checkNotNumber(String number) {
        try {
            Integer.parseInt(number);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("로또 넘버는 숫자여야 합니다. 입력한 문자 : %s", number));
        }
    }

    @Override
    public boolean equals(Object lottoNumber) {
        return (lottoNumber instanceof LottoNumber) &&  this.number == ((LottoNumber)lottoNumber).number;
    }

    @Override
    public int hashCode(){
        return Objects.hash(number);
    }

    @Override
    public int compareTo(Object o) {
        if (this.number > ((LottoNumber)o).number){
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString(){
        return Integer.toString(number);
    }
}
