package domain.lottonumbers.lottonumber;

public class LottoNumber implements Comparable<LottoNumber> {

    public static final int MIN_BOUND = 1;
    public static final int MAX_BOUND = 45;

    private final int number;

    public LottoNumber(int number) {
        this.number = number;
    }

    @Override
    public int compareTo(LottoNumber number) {
        return this.number - number.number;
    }

    public static LottoNumber of(int number) {
        validateNumber(number);
        return LottoNumberRepository.cache.get(number);
    }

    private static void validateNumber(int number) {
        if (number < MIN_BOUND || number > MAX_BOUND) {
            throw new IllegalArgumentException("1부터 45까지의 숫자만 가능합니다.");
        }
    }

    public int getValue() {
        return this.number;
    }
}
