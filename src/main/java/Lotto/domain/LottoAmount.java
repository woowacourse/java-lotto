package Lotto.domain;

import Lotto.utils.NumberParser;

public class LottoAmount {
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
        if(amount < 0) {
            throw new IllegalArgumentException("로또는 0장 이상이어야 합니다.");
        }
    }

    public int getLottoAmount() {
        return lottoAmount;
    }
}
