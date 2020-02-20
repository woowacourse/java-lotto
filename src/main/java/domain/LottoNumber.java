package domain;


import java.util.Objects;

public class LottoNumber implements Comparable {
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;
    private final int number;

    public LottoNumber(String number) {
        number = number.trim();
        checkNotNumber(number);
        int numberIntegerValue = Integer.parseInt(number);
        checkLottoRange(numberIntegerValue);
        this.number = numberIntegerValue;
    }

    public LottoNumber(final int number) {
        checkLottoRange(number);
        this.number = number;
    }

    private void checkLottoRange(final int number) {
        if (isNotLottoNumber(number)) {
            throw new IllegalArgumentException("로또 숫자 범위를 넘어섰습니다.");
        }
    }

    private boolean isNotLottoNumber(final int number) {
        return number > MAX_LOTTO_NUMBER || number < MIN_LOTTO_NUMBER;
    }

    private void checkNotNumber(final String number) {
        try {
            Integer.parseInt(number);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("로또 넘버는 숫자여야 합니다. 입력한 문자 : %s", number));
        }
    }

    @Override
    public boolean equals(final Object lottoNumber) {
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
