package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lotto.model.Money;
import org.junit.jupiter.api.Test;

public class InputValidatorTest {

    @Test
    void 가격_검증_테스트_정상() {
        Money money = InputValidator.validateMoney("10000");
        assertThat(money.getBuyingLottoCount()).isEqualTo(10);
    }

    @Test
    void 가격_검증_테스트_음수() {
        assertThatThrownBy(() ->
                InputValidator.validateMoney("-1"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("[ERROR] 유효한 입력이 아닙니다.");
    }

    @Test
    void 가격_검증_테스트_문자() {
        assertThatThrownBy(() ->
                InputValidator.validateMoney("L"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("[ERROR] 유효한 입력이 아닙니다.");
    }

    @Test
    void 로또번호_정상_테스트() {
        String[] actual = InputValidator.validateLottoNumbers("1,2,3,4,5,6");
        assertThat(actual).contains("1", "2", "3", "4", "5", "6");
    }

    @Test
    void 로또번호_문자_포함_테스트() {
        assertThatThrownBy(() ->
                InputValidator.validateLottoNumbers("1,2,3,4,ㅇ, 6"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }
}
