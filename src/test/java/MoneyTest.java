import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {

    @ParameterizedTest
    @CsvSource(value = {"14000, 14", "1000, 1", "2400000, 2400", "170000, 170"})
    @DisplayName("구입 금액에 따른 생성할 수 있는 로또 개수 테스트")
    public void generateLottoTest(int money, int expectedCount) {
        Money inputMoney = new Money(money);

        int count = inputMoney.generateCount();
        assertThat(count).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("1000원 미만이 입력되었을 때, 예외 발생 테스트 ")
    public void inputUnder1000Test() {
        assertThatThrownBy(() -> new Money(10))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
