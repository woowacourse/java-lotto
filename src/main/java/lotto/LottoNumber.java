package lotto;

public class LottoNumber {

    private static final int MIN_LOTTO_NUM = 1;

    private static final int MAX_LOTTO_NUM = 45;

    private final int lottoNum;

    public static LottoNumber from(int lottoNum) {
        if (lottoNum < MIN_LOTTO_NUM || lottoNum > MAX_LOTTO_NUM) {
            throw new IllegalArgumentException("1~45의 숫자만 입력해야 합니다.");
        }

        return new LottoNumber(lottoNum);
    }

    private LottoNumber(int lottoNum) {
        this.lottoNum = lottoNum;
    }
}
