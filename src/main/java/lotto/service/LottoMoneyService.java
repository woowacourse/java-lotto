package lotto.service;

import lotto.domain.LottoMoney;
import lotto.util.ExceptionHandler;
import lotto.util.StringConverter;

public class LottoMoneyService {

    public LottoMoney createLottoMoney(String input) {
        int amount = StringConverter.parseToInt(input, ExceptionHandler.INVALID_LOTTO_MONEY_FORMAT);
        return new LottoMoney(amount);
    }
}
