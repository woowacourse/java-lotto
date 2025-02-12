package domain;

import exception.LottoException;
import utility.StringUtility;

public class LottoDispenser {

    private final int LOTTO_MONEY_UNIT = 1000;
    private String INVALID_BUY_MONEY = "유효하지 않은 구매 금액입니다.";

    public LottoDispenser(String buyMoney) {
        validateLottoDispenser(buyMoney);
    }

    private void validateLottoDispenser(String buyMoney) {
        if(!StringUtility.isNumber(buyMoney)){
            throw new LottoException(INVALID_BUY_MONEY);
        }
        int buyMoneyNumber = Integer.parseInt(buyMoney);
        if(buyMoneyNumber == 0){
            throw new LottoException(INVALID_BUY_MONEY);
        }
        if(buyMoneyNumber % LOTTO_MONEY_UNIT != 0){
            throw new LottoException(INVALID_BUY_MONEY);
        }
    }
}
