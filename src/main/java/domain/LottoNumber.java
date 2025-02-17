package domain;

import static domain.properties.LottoProperties.MAX_LOTTO_NUMBER;
import static domain.properties.LottoProperties.MIN_LOTTO_NUMBER;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    private final int lottoNumber;

    private LottoNumber(int lottoNumber) {
        validate(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    public static LottoNumber of(int number) {
        return new LottoNumber(number);
    }

    private void validate(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(
                    String.format("로또 번호는 %d부터 %d사이여야 합니다.", MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
            );
        }
    }

    public boolean isSameValue(LottoNumber compared) {
        return (lottoNumber == compared.lottoNumber);
    }

    public int getLottoNumber() {
        return lottoNumber;
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.lottoNumber, other.lottoNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LottoNumber that)) {
            return false;
        }
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(lottoNumber);
    }
}
