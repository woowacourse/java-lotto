package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("돈 객체 테스트")
class MoneyTest {
    @ParameterizedTest
    @ValueSource(strings = {" ", "만 원"})
    @DisplayName("돈 객체 생성자")
    void moneyTest1(String money) {
        assertThatThrownBy(() -> new Money(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("0원 이상 숫자를 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1700", "-1", "50001"})
    @DisplayName("돈 객체 생성자")
    void moneyTest2(String money) {
        assertThatThrownBy(() -> new Money(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("천 원 단위로만 구매 가능합니다.");
    }
}