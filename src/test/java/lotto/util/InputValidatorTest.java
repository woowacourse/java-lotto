package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class InputValidatorTest {

    @Test
    void 가격_검증_테스트_정상() {
        int price = InputValidator.validatePrice("10000");
        assertThat(price).isEqualTo(10);
    }

    @Test
    void 가격_검증_테스트_음수() {
        assertThatThrownBy(() ->
                InputValidator.validatePrice("-1"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("[ERROR] 유효한 입력이 아닙니다.");
    }

    @Test
    void 가격_검증_테스트_문자() {
        assertThatThrownBy(() ->
                InputValidator.validatePrice("L"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("[ERROR] 유효한 입력이 아닙니다.");
    }

    @Test
    void 당첨번호_검증_테스트_정상() {
        List<Integer> splitWinningNumbers = InputValidator.validateWinningNumbers("1,2,3,4,5,6");
        assertThat(splitWinningNumbers).contains(1,2,3,4,5,6);
    }

    @Test
    void 당첨번호_검증_테스트_길이() {
        assertThatThrownBy(() ->
                InputValidator.validateWinningNumbers("1,2,3"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("[ERROR] 6개의 숫자가 입력되지 않았습니다.");
    }

    @Test
    void 당첨번호_검증_테스트_중복() {
        assertThatThrownBy(() ->
                InputValidator.validateWinningNumbers("1,2,2,3,4,5"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("[ERROR] 중복된 숫자가 존재합니다.");
    }

    @Test
    void 당첨번호_검증_테스트_범위() {
        assertThatThrownBy(() ->
                InputValidator.validateWinningNumbers("1,2,3,4,46,8"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("[ERROR] 숫자의 범위가 잘못되었습니다.");
    }

    @Test
    void 보너스번호_검증_테스트_정상() {
        int bonusNumber = InputValidator.validateBonusNumber("7", List.of(1, 2, 3, 4, 5, 6));
        assertThat(bonusNumber).isEqualTo(7);
    }

    @Test
    void 보너스번호_검증_테스트_중복() {
        assertThatThrownBy(() ->
                InputValidator.validateBonusNumber("7", List.of(2, 3, 4, 5, 6, 7)))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 보너스번호_검증_테스트_범위() {
        assertThatThrownBy(() ->
                InputValidator.validateBonusNumber("47", List.of(1, 2, 3, 4, 5, 6)))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }
}
