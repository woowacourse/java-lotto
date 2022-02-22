import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {

    @Test
    @DisplayName("구입 금액에 따른 생성할 수 있는 로또 개수 테스트")
    public void generateLottoTest() {
        Money inputMoney = new Money(14000);

        int count = inputMoney.generateCount();
        assertThat(count).isEqualTo(14);
    }

    @Test
    @DisplayName("1000원 미만이 입력되었을 때, 예외 발생 테스트 ")
    public void inputUnder1000Test() {
        assertThatThrownBy(() -> new Money(10))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
