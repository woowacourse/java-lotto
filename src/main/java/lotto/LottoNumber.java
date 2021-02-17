package lotto;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MIN_NUMBER_RANGE = 1;
    private static final int MAX_NUMBER_RANGE = 45;
    private static final String RANGE_ERROR_MESSAGE = "숫자는 1~45 사이의 숫자여야한다.";

    public final int number;

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    private void validateRange(int number) {
        if (number >= MIN_NUMBER_RANGE && number <= MAX_NUMBER_RANGE) {
            return;
        }
        throw new IllegalArgumentException(RANGE_ERROR_MESSAGE);
    }

    @Override //equals 함부로 쓰면 안된다고 했는데, 우리팀의 경우 상속 받은 것들 비교를 제대로 못해줘서 디버깅 한참 걸림. 다른예시좀?
    public boolean equals(Object o) {
        return number == ((LottoNumber) o).number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        return Integer.compare(number, lottoNumber.number);
    }
}
