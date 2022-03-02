package lotto.domain;

import lotto.exception.MoneyException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

//    @ParameterizedTest(name = "음수가 입력될 경우 예외 발생 - case : {0}")
//    @ValueSource(strings = {"-1", "-1200", "-3000"})
//    void checkPositive(String input) {
//        Assertions.assertThatThrownBy(() -> Money.generateMoneyByString(input))
//                .isInstanceOf(MoneyException.class)
//                .hasMessage("구입금액이 음수일 수 없습니다.");
//    }
//
//    @ParameterizedTest(name = "1000원 단위가 아닐 경우 예외 발생 - case : {0}")
//    @ValueSource(strings = {"100", "1200", "1234"})
//    void checkUnit(String input) {
//        Assertions.assertThatThrownBy(() -> Money.generateMoneyByString(input))
//                .isInstanceOf(MoneyException.class)
//                .hasMessage("구입금액은 1000원 단위만 가능합니다.");
//    }

//    @Test
//    @DisplayName("투입한 금액과 당첨으로 얻은 수익으로 수익률 계산")
//    void getProfitRate() {
//        Money money = Money.generateMoneyByString("14000");
//        Assertions.assertThat(money.toStringProfitRateUntilSecondDecimal(5000)).isEqualTo("0.35");
//    }
}
