package Lotto.domain;

import Lotto.utils.NumberParser;

public class LottoCount {
    private final static String LOTTO_COUNT_BIGGER_THAN_ZERO = "로또는 0장 이상이어야 합니다.";

    private int lottoCount;

    public LottoCount(String inputAsString) {
        int count = NumberParser.parseIntoOneNumber(inputAsString);
        validateLottoCount(count);
        this.lottoCount = count;
    }

    public LottoCount(int count) {
        validateLottoCount(count);
        this.lottoCount = count;
    }

    private void validateLottoCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException(LOTTO_COUNT_BIGGER_THAN_ZERO);
        }
    }

    public int getLottoCount() {
        return lottoCount;
    }
}
