package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {

    @Test
    @DisplayName("구입 금액에 따른 생성할 수 있는 로또 개수 테스트")
    public void generateLottoTest() {
        int inputMoney = 14000;
        int unit = 1000;
        int actualCount = inputMoney / unit;
        Money money = new Money(inputMoney);
        int count = money.generateCount();
        assertThat(count).isEqualTo(actualCount);
    }

    @Test
    @DisplayName("1000원 미만이 입력되었을 때, 예외 발생 테스트 ")
    public void inputUnder1000Test() {
        int inputMoney = 10;
        assertThatThrownBy(() -> new Money(inputMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
