package lotto.domain;

public class LottoNumber {

    private final int value;

    private LottoNumber(int value) {
        this.value = value;
    }

    public static LottoNumber valueOf(int i) {
        if (i >= LottoNumberCache.low && i <= LottoNumberCache.high)
            return LottoNumberCache.cache[i + (-LottoNumberCache.low)];
        throw new IllegalArgumentException("범위 이외의 숫자 입력입니다.");
    }

    private static class LottoNumberCache {
        static final int low = 1;
        static final int high = 45;
        static final LottoNumber[] cache;

        static {
            cache = new LottoNumber[(high - low) + 1];
            int j = low;
            for(int k = 0; k < cache.length; k++)
                cache[k] = new LottoNumber(j++);
        }

        private LottoNumberCache() {}
    }

    public int intValue() {
        return value;
    }
}
