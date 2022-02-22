package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @DisplayName("가격을 구매할 로또장 수로 변환하는데 성공한다")
    @Test
    void convertToLottoCount() {
        final Money money = new Money(3500);
        assertThat(money.calculateLottoCount()).isEqualTo(3);
    }
}
