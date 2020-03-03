package lotto.domain;

import lotto.exception.UnderLottoUnitMoney;
import lotto.utils.StringUtils;
import lotto.utils.ValidationUtils;

public class Money {

    private static final String UNDER_LOTTO_UNIT_RETURN_CHANGE = "한장도 구매할수 없습니다. 거스름돈 %s원 반환 재입력 해주세요";
    private static final int LOTTO_UNIT = 1000;
    private Long money;


    public Money(String money) {
        validateMoney(money);

        this.money = StringUtils.stringToLong(money);
    }

    private void validateMoney(String money) {
        ValidationUtils.validateIntegerNumberFormat(money);
        ValidationUtils.validatePositiveNumber(money);
        validateLottoUnit(money);
    }

    private static void validateLottoUnit(String input) {
        if (StringUtils.stringToInt(input) < LOTTO_UNIT) {
            throw new UnderLottoUnitMoney(String.format(UNDER_LOTTO_UNIT_RETURN_CHANGE, input));
        }
    }

    public String changeMoney() {
        return String.valueOf(money % LOTTO_UNIT);
    }

    public String generateLottoTicketCount() {
        return String.valueOf(money / LOTTO_UNIT);
    }

    public Long getMoney() {
        return money;
    }
}
