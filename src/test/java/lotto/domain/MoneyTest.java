package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @DisplayName("가격을 구매할 로또장 수로 변환하는데 성공한다")
    @Test
    void convertToLottoCount() {
        final Money money = new Money(3500);
        assertThat(money.calculateLottoCount()).isEqualTo(3);
    }

    @DisplayName("기준보다 낮은 금액 입력시 에러를 발생한다.")
    @Test
    void createMoneyExceptionByLowerThanStandard() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Money(10))
                .withMessage("[ERROR] 로또를 구매할 수 없는 금액입니다.");
    }

    @DisplayName("보유금액보다 많은 로또 구매시 예러를 발생한다.")
    @Test
    void buyLottoExceptionByLackMoney() {
        final Money money = new Money(3000);
        final int manualCounts = 5;
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> money.calculatePurchaseCounts(manualCounts))
                .withMessage("[ERROR] 보유 금액보다 많은 로또를 구매할 수 없습니다.");
    }
}
