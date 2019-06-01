package lotto.domain;

import lotto.Exception.InvalidCustomLottoCountException;
import lotto.Exception.OverPriceException;

public class CustomLottoCount {
    private static final int MIN_CUSTOM_LOTTO_COUNT = 0;
    private final int customLottoCount;

    public CustomLottoCount(final int customLottoCount, Money money) {
        if(customLottoCount <  MIN_CUSTOM_LOTTO_COUNT ){
            throw new InvalidCustomLottoCountException();
        }

        if(money.isOverPrice(customLottoCount)){
            throw new OverPriceException();
        }

        this.customLottoCount = customLottoCount;
    }

    public int getCustomLottoCount() {
        return customLottoCount;
    }
/*
    public boolean isNotMatchCount(String[] customLottoNumbersInput) {
        return customLottoNumbersInput.length != customLottoCount;
    }*/
}
