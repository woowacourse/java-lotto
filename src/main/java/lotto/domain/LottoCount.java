package lotto.domain;

import lotto.exception.ExceedMoneyException;
import lotto.util.StringUtil;

public class LottoCount {
    private final int manualLottoCount;

    private LottoCount(String input) {
        this.manualLottoCount = Integer.parseInt(input);
    }

    private static void validate(String input) {
        StringUtil.checkNull(input);
        StringUtil.checkBlank(input);
        StringUtil.checkNumberFormat(input);
        StringUtil.checkRange(input);
    }

    public static LottoCount create(String input, PurchaseMoney money){
        validate(input);
        validateMoney(input,money);
        return new LottoCount(input);
    }

    private static void validateMoney(String input, PurchaseMoney money) {
        if(money.parseToPiece() < Integer.parseInt(input)){
            throw new ExceedMoneyException(money.parseToPiece()+"장 이하만 구매가 가능합니다.");
        }
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }

    public int getAutoLottoCount(PurchaseMoney money) {
        return money.parseToPiece() - this.manualLottoCount;
    }
}
