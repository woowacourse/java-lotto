package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoGameTest {

    @ParameterizedTest
    @ValueSource(ints = {-2000, 999, 0})
    @DisplayName("1~45 범위 밖의 수로 생성하면 예외 발생")
    void moneyUnderMinimum_constructor_throwException(int money) {
        assertThatThrownBy(() -> new LottoGame(money))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
