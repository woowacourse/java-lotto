package lotto;

import lotto.domain.lotto.LottoStore;
import lotto.domain.lotto.ManualLotto;

public class Money {

    private static final String ERROR_POSITIVE = "음수는 불가능합니다.";
    private static final String ERROR_SPACE = "공백은 불가능합니다.";
    private static final String ERROR_BUY_FAIL = "구매 할 수 없는 로또 수량입니다.";
    private int price;

    public Money(String price) {
        validMoney(price);
        validPositive(price);
        this.price = Integer.parseInt(price);
    }

    private void validPositive(String price) {
        if (Integer.parseInt(price) < 0) {
            throw new IllegalArgumentException(ERROR_POSITIVE);
        }
    }

    private void validMoney(String price) {
        try {
            Integer.parseInt(price);
        } catch (Exception error) {
            throw new IllegalArgumentException(ERROR_SPACE);
        }
    }

    public int getPrice(){
        return this.price;
    }

    public void validNumManual(ManualLotto numManual) {
        if( price - (numManual.getNumLotto()* LottoStore.LOTTO_PRICE ) < 0){
            throw new IllegalArgumentException(ERROR_BUY_FAIL);
        }
    }
}