package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.constant.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    void 수익률_계산_확인() {
        //given
        int totalProfit = 5000;
        String purchaseMoney = "14000";

        //when
        Money money = new Money(purchaseMoney);

        //then
        Assertions.assertThat(money.calculateProfit(totalProfit)).isEqualTo(0.35);
    }

    @Test
    void 로또_구입_개수() {
        //given
        String purchaseMoney = "14000";

        //when
        Money money = new Money(purchaseMoney);

        //then
        Assertions.assertThat(money.countsLotto()).isEqualTo(14);
    }

    @Test
    void 구입_금액_천원_단위_검증() {
        //given
        String invalidMoney = "1001";

        //when & then
        assertThatThrownBy(() -> new Money(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.PURCHASE_UNIT_ERROR.getMessage());
    }

    @Test
    void 구입_금액_숫자_검증() {
        //given
        String invalidMoney = "천원";

        //when & then
        assertThatThrownBy(() -> new Money(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.PURCHASE_FORMAT_ERROR.getMessage());
    }

    @Test
    void 구입_금액_음수_검증() {
        //given
        String invalidMoney = "-1000";

        //when & then
        assertThatThrownBy(() -> new Money(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.PURCHASE_MINIMUM_ERROR.getMessage());
    }

    @Test
    void 구입_금액_최대_검증() {
        //given
        String invalidMoney = "101000";

        //when & then
        assertThatThrownBy(() -> new Money(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.PURCHASE_MAXIMUM_ERROR.getMessage());
    }

}