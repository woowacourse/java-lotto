package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import lotto.model.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    void 생성_및_비교() {
        // given, when
        Money money1 = Money.priceOf(1000);
        Money money2 = Money.priceOf(1000);

        // then
        assertThat(money1).isEqualTo(money2);
    }

    @DisplayName("금액은 마이너스가 될 수 없습니다.")
    @Test
    void 유효성_검사() {
        assertThatIllegalArgumentException().isThrownBy(() -> Money.priceOf(-1000));
    }
}