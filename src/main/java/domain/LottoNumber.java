package domain;

import domain.properties.LottoProperties;

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
        if (number < LottoProperties.MIN_NUMBER || number > LottoProperties.MAX_NUMBER) {
            throw new IllegalArgumentException(
                    String.format("로또 번호는 %d부터 %d사이여야 합니다.", LottoProperties.MIN_NUMBER, LottoProperties.MAX_NUMBER)
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
}
