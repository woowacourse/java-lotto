package lotto.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class InputValidatorTest {

    @Test
    void 가격_검증테스트_음수() {
        Assertions.assertThatThrownBy(() ->
            InputValidator.validatePrice("-1"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("[ERROR] 유효한 입력이 아닙니다.");
    }

    @Test
    void 가격_검증테스트_문자() {
        Assertions.assertThatThrownBy(() ->
                        InputValidator.validatePrice("L"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("[ERROR] 유효한 입력이 아닙니다.");
    }

    @Test
    void 당첨번호_검증테스트_길이() {
        Assertions.assertThatThrownBy(() ->
                        InputValidator.validateWinningNumbers("1,2,3"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("[ERROR] 6개의 숫자가 입력되지 않았습니다.");
    }

    @Test
    void 당첨번호_검증테스트_중복() {
        Assertions.assertThatThrownBy(() ->
                        InputValidator.validateWinningNumbers("1,2,2,3,4,5"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("[ERROR] 중복된 숫자가 존재합니다.");
    }

    @Test
    void 당첨번호_검증테스트_범위() {
        Assertions.assertThatThrownBy(() ->
                        InputValidator.validateWinningNumbers("1,2,3,4,46,8"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("[ERROR] 숫자의 범위가 잘못되었습니다.");
    }

    @Test
    void 보너스번호_검증테스트_중복() {
        Assertions.assertThatThrownBy(() ->
                        InputValidator.validateBonusNumber("7", List.of(2,3,4,5,6,7)))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 보너스번호_범위테스트() {
       Assertions.assertThatThrownBy(() ->
               InputValidator.validateBonusNumber("47", List.of(1,2,3,4,5,6)))
               .isInstanceOf(RuntimeException.class)
               .hasMessageContaining("[ERROR]");
    }
}
