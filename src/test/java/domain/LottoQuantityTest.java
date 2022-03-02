package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoQuantityTest {

    @Test
    @DisplayName("입력한 수동으로 구매할 수량이 음수인 경우, 예외를 발생한다")
    void checkValidManualLottoQuantity() {
        final int manualLottoQuantity = -1;
        final Money money = new Money(1000);

        assertThatThrownBy(() -> LottoQuantity.of(manualLottoQuantity, money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 수량은 반드시 양수여야 합니다.");
    }

    @Test
    @DisplayName("입력한 수동으로 구매할 수량이 지불한 돈을 초과한 경우, 예외를 발생한다")
    void checkEnoughMoney() {
        final int manualLottoQuantity = 10;
        final Money money = new Money(1000);

        assertThatThrownBy(() -> LottoQuantity.of(manualLottoQuantity, money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력한 수량만큼 구매할 수 없습니다.");
    }

    @Test
    @DisplayName("입력한 수동으로 구매할 수량이 유효한 경우, 정상적으로 객체가 생성된다")
    void createLottoQuantity() {
        final int manualLottoQuantity = 10;
        final Money money = new Money(10000);
        final int expectedManualLottoQuantity = 10;
        final int expectedAutoLottoQuantity = 0;

        final LottoQuantity lottoQuantity = LottoQuantity.of(manualLottoQuantity, money);

        assertThat(lottoQuantity.getManualLotto()).isEqualTo(expectedManualLottoQuantity);
        assertThat(lottoQuantity.getAutoLotto()).isEqualTo(expectedAutoLottoQuantity);
    }
}
