package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lotto.model.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputValidatorTest {

    @Test
    void 가격_검증_테스트_정상() {
        Money money = InputValidator.validateMoney("10000");
        assertThat(money.getBuyingLottoCount()).isEqualTo(10);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "L", ""})
    void 가격_검증_테스트_음수_및_문자(String money) {
        assertThatThrownBy(() ->
                InputValidator.validateMoney(money))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("[ERROR] 유효한 입력이 아닙니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6", "1, 2, 3, 4, 5, 6", "1, 2,3, 4,5 ,6"})
    void 로또번호_정상_테스트(String numbers) {
        String[] actual = InputValidator.validateLottoNumbers(numbers);
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
