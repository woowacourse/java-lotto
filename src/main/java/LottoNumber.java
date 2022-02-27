import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;

    private static final int CACHE_INDEX = 1;
    private static final String INVALID_RANGE_MESSAGE = "[ERROR] 번호는 1부터 45 이내의 숫자여야 합니다.";
    private static final List<LottoNumber> CACHE = new ArrayList<>();

    static {
        for (int number = MIN_LOTTO_NUMBER; number <= MAX_LOTTO_NUMBER; number++) {
            CACHE.add(new LottoNumber(number));
        }
    }

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(int number) {
        try {
            return CACHE.get(number - CACHE_INDEX);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(INVALID_RANGE_MESSAGE);
        }
    }

    public static List<LottoNumber> values() {
        return CACHE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return number - o.number;
    }
}
