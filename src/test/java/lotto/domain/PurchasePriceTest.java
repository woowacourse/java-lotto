package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PurchasePriceTest {

    @DisplayName("입력값이 없을 때 예외발생")
    @NullAndEmptySource
    @ParameterizedTest
    void noInputTest(String input) {
        assertThatThrownBy(() -> {
                    new PurchasePrice(input);
                }
        ).isInstanceOf(RuntimeException.class)
                .hasMessage("구입금액을 입력해 주세요.");
    }

    @DisplayName("숫자가 아닌 값이 들어왔을때 예외발생")
    @ParameterizedTest
    @ValueSource(strings = {"1000원", "1,000", "천원", "5000$"})
    void inputTypeTest(String input) {
        assertThatThrownBy(() -> {
                    new PurchasePrice(input);
                }
        ).isInstanceOf(RuntimeException.class)
                .hasMessage("구입금액은 숫자만 입력 가능합니다.");
    }

    @DisplayName("1000 미만의 숫자가 들어왔을때 예외발생")
    @ParameterizedTest
    @ValueSource(strings = {"999", "0", "-1000"})
    void inputUnderMinimumPriceTest(String input) {
        assertThatThrownBy(() -> {
                    new PurchasePrice(input);
                }
        ).isInstanceOf(RuntimeException.class)
                .hasMessageEndingWith("원 이상 구매하셔야 합니다.");
    }
}
