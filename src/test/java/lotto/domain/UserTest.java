package lotto.domain;

import lotto.exception.MoneyException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UserTest {

    @ParameterizedTest(name = "음수가 입력될 경우 예외 발생 - case : {0}")
    @ValueSource(strings = {"-1", "-1200", "-3000"})
    void generateByString_checkPositive(String input) {
        Assertions.assertThatThrownBy(() -> User.generateByString(input))
                .isInstanceOf(MoneyException.class)
                .hasMessage("투입금액이 음수일 수 없습니다.");
    }

    @ParameterizedTest(name = "1000원 단위가 아닐 경우 예외 발생 - case : {0}")
    @ValueSource(strings = {"100", "1200", "1234"})
    void generateByString_checkUnit(String input) {
        Assertions.assertThatThrownBy(() -> User.generateByString(input))
                .isInstanceOf(MoneyException.class)
                .hasMessage("투입금액은 1000원 단위만 가능합니다.");
    }

    @Test
    @DisplayName("남은금액 만큼 자동 로또티켓을 구입")
    void buyAllLottosByAuto() {
        User user = User.generateByString("9000");
        user.buyAllLottosByAuto();
        Assertions.assertThat(user.getLottos().size()).isEqualTo(9);
    }

    @Test
    @DisplayName("사용한 금액이 투입 금액을 초과할 경우 예외 발생")
    void spendMoney() {
        User user = User.generateByString("1000");
        user.spendMoney(1000);
        Assertions.assertThatThrownBy(() -> user.spendMoney(1000))
                .isInstanceOf(MoneyException.class)
                .hasMessage("투입한 금액을 모두 사용했습니다.");
    }
}
