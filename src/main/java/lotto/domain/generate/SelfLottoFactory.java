package lotto.domain.generate;

import lotto.domain.Lotto;
import lotto.utils.Converter;


public class SelfLottoFactory implements LottoFactory {
    private String selfLottoInput;

    SelfLottoFactory(String selfLottoInput) {
        this.selfLottoInput = selfLottoInput;
    }

    @Override
    public Lotto create() {
        return new Lotto(Converter.convertNumbers(selfLottoInput));
    }

}
