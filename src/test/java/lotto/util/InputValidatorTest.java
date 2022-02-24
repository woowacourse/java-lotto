package lotto.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputValidatorTest {

    @Test
    @DisplayName("구입금액으로 문자가 입력될 경우")
    void purchaseAmountTest1() {
        assertThatThrownBy(() -> InputValidator.validatePurchaseAmount("IOOO"))
            .isInstanceOf(NumberFormatException.class)
            .hasMessage("[ERROR] 숫자를 입력해주세요.");
    }

    @Test
    @DisplayName("구입금액이 로또 가격보다 적을 경우")
    void purchaseAmountTest2() {
        assertThatThrownBy(() -> InputValidator.validatePurchaseAmount("999"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 금액이 적어 로또를 구입할 수 없습니다.");
    }

    @Test
    @DisplayName("당첨 번호와 보너스 볼의 중복 검증")
    void winningNumbersAndBonusBallTest() {
        List<String> list = new ArrayList<>(List.of("1", "2", "3", "4", "5", "6"));

        assertThatThrownBy(() -> InputValidator.validateContain(list, "1"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 중복된 숫자가 존재합니다.");
    }

    @Test
    @DisplayName("로또 번호의 숫자가 6개가 아닐 경우")
    void lottoTest1() {
        assertThatThrownBy(() -> InputValidator.validateLotto(List.of("1", "2", "3")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 6개의 숫자가 입력되지 않았습니다.");
    }

    @Test
    @DisplayName("로또 번호가 중복될 경우")
    void lottoTest2() {
        assertThatThrownBy(() -> InputValidator.validateLotto(List.of("1", "1", "2", "3", "4", "5")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 중복된 숫자가 존재합니다.");
    }

    @Test
    @DisplayName("로또 번호에 문자가 입력될 경우")
    void lottoTest3() {
        assertThatThrownBy(() -> InputValidator.validateLotto(List.of("a", "b", "d", "4", "5", "6")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 숫자를 입력해주세요.");
    }

    @Test
    @DisplayName("로또번호의 각 숫자가 1 ~ 45 사이의 값이 아닐 경우")
    void lottoTest4() {
        assertThatThrownBy(() -> InputValidator.validateLotto(List.of("48", "49", "50", "4", "5", "6")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 1 ~ 45 사이의 숫자를 입력해주세요.");
    }

    @Test
    @DisplayName("보너스볼에 문자가 입력될 경우")
    void bonusBallTest1() {
        assertThatThrownBy(() -> InputValidator.validateBonusNumber("LO"))
            .isInstanceOf(NumberFormatException.class)
            .hasMessage("[ERROR] 숫자를 입력해주세요.");
    }

    @Test
    @DisplayName("보너스볼이 1 ~ 45 사이의 값이 아닐 경우")
    void bonusBallTest2() {
        assertThatThrownBy(() -> InputValidator.validateBonusNumber("47"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 1 ~ 45 사이의 숫자를 입력해주세요.");
    }
}
