package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final int lottoNumber;

    private LottoNumber(Integer number) {
        validateLottoNumber(number);
        this.lottoNumber = number;
    }

    private void validateLottoNumber(Integer number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1 이상 45 이하 입니다.");
        }
    }

    public static LottoNumber valueOf(int number) {
        if (number >= MIN_NUMBER && number <= MAX_NUMBER) {
            return LottoNumberCache.cache.get(number);
        }
        return new LottoNumber(number);
    }

    private static class LottoNumberCache {

        private static final List<LottoNumber> cache = new ArrayList<>();

        static {
            IntStream.range(MIN_NUMBER, MAX_NUMBER + 1)
                    .boxed()
                    .forEach(number -> cache.add(new LottoNumber(number)));
        }

        private LottoNumberCache() {
        }


    }

    public int getLottoNumber() {
        return lottoNumber;
    }

    @Override
    public int compareTo(LottoNumber o) {
        if (this.lottoNumber > o.lottoNumber) {
            return 1;
        }
        if (this.lottoNumber == o.lottoNumber) {
            return 0;
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public String toString() {
        return lottoNumber+"";
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }
}
