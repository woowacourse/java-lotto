import static org.assertj.core.api.Assertions.assertThat;

import domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("생성자에 integer숫자가 들어간다")
    void make_money3() {
        Money money = new Money(12000);
        assertThat(money.money()).isEqualTo(12000);
    }
}
