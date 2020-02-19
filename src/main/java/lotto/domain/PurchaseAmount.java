package lotto.domain;

import lotto.util.InputValidationUtil;

public class PurchaseAmount {

    private static final int LOTTO_PURCHASE_UNIT = 1000;
    private int purchaseAmount;

    public PurchaseAmount(String purchaseAmount) {
        this.purchaseAmount = InputValidationUtil.isNumber(purchaseAmount);
        InputValidationUtil.isPositiveNumber(this.purchaseAmount);
        underLottoUnit();
    }

    public int lottoTicket() {
        return this.purchaseAmount / LOTTO_PURCHASE_UNIT;
    }

    public int giveChangeMoney() {
        return this.purchaseAmount % LOTTO_PURCHASE_UNIT;
    }


    public void underLottoUnit(){
        if(this.purchaseAmount<LOTTO_PURCHASE_UNIT){
            throw new IllegalArgumentException("한개도 구매할 수 없습니다." + this.purchaseAmount + "원을 반환합니다.");
        }
    }
}
