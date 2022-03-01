package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {1000, 1001, 1500, 1999})
    @DisplayName("구매할수 있는 로또 개수가 1개인지 테스트")
    public void generateLottoOneTest(int inputMoney) {
        Money money = new Money(inputMoney);
        int count = money.generateCount();

        assertThat(count).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {2000, 2001, 2500, 2999})
    @DisplayName("구매할수 있는 로또 개수가 2개인지 테스트")
    public void generateLottoTwoTest(int inputMoney) {
        Money money = new Money(inputMoney);
        int count = money.generateCount();

        assertThat(count).isEqualTo(2);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 999, 1})
    @DisplayName("1000원 미만이 입력되었을 때, 예외 발생 테스트 ")
    public void inputUnder1000Test() {
        int inputMoney = 10;
        assertThatThrownBy(() -> new Money(inputMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 1001, 1500, 1999, 2222})
    @DisplayName("경계값 테스트")
    public void boundaryValueTest(int inputMoney) {
        assertDoesNotThrow(() -> new Money(inputMoney));
    }
}
