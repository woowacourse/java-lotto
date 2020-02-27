package Lotto.domain;

import Lotto.utils.NumberParser;

public class LottoAmount {
    private final static String LOTTO_AMOUNT_BIGGER_THAN_ZERO = "로또는 0장 이상이어야 합니다.";

    private int lottoAmount;

    public LottoAmount(String inputAsString) {
        int amount = NumberParser.parseIntoOneNumber(inputAsString);
        validate(amount);
        this.lottoAmount = amount;
    }

    public LottoAmount(int amount) {
        validate(amount);
        this.lottoAmount = amount;
    }

    private void validate(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(LOTTO_AMOUNT_BIGGER_THAN_ZERO);
        }
    }

    public int getLottoAmount() {
        return lottoAmount;
    }
}
