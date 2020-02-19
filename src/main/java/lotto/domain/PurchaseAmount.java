package lotto.domain;

public class PurchaseAmount {

    public static final int LOTTO_PURCHASE_UNIT = 1000;
    int purchaseAmount;

    public PurchaseAmount(String purchaseAmount) {
        this.purchaseAmount = isNumber(purchaseAmount);
        isPositiveNumber(this.purchaseAmount);
        underLottoUnit();
    }

    public int lottoTicket() {
        return this.purchaseAmount / LOTTO_PURCHASE_UNIT;
    }

    public int giveChangeMoney() {
        return this.purchaseAmount % LOTTO_PURCHASE_UNIT;
    }

    private int isNumber(String purchaseAmount) {
        try {
            return Integer.parseInt(purchaseAmount);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자가 아닙니다.");
        }
    }
    private void isPositiveNumber(int purchaseAmount) {
        if(purchaseAmount < 0){
            throw new IllegalArgumentException("음수입니다.");
        }
    }

    public void underLottoUnit(){
        if(this.purchaseAmount<LOTTO_PURCHASE_UNIT){
            throw new IllegalArgumentException("한개도 구매할 수 없습니다." + this.purchaseAmount + "원을 반환합니다.");
        }
    }
}
