package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoSizeTest {
    @Test
    @DisplayName("만들어진 로또 사이즈가 0 이상인지 확인")
    void GenerateLottos() {
        assertThatThrownBy(() -> {
            new LottoSize(-1);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또는 0장 이상 구매하셔야 합니다.");
    }

    @Test
    @DisplayName("수동으로 구매하고 자동으로 올바르게 계산되는지 확인")
    void validatePositiveManualLottoSizeTest() {
        LottoSize manualLottoSize = new LottoSize(3);
        int actual = manualLottoSize.getRestOfLottoSize(12);

        assertThat(actual).isEqualTo(9);
    }

    @Test
    @DisplayName("나머지 로또를 올바르게 생성하지 않는 경우 예외처리")
    void getAutoLottoSizeTest() {
        assertThatThrownBy(() -> {
            LottoSize manualLottoSize = new LottoSize(2);
            manualLottoSize.getRestOfLottoSize(1);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또는 0장 이상 구매하셔야 합니다.");
    }

    @Test
    @DisplayName("구매한 금액보다 원하는 수동 로또 수가 더 많은 경우 예외처리")
    void validateLottoSizeWithMoney() {
        assertThatThrownBy(() -> {
            LottoSize manualLottoSize = new LottoSize(3);
            Money money = new Money(1000);
            manualLottoSize.validateLottoSizeWithMoney(money);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("수동 로또의 수가 구입 금액을 넘을 수 없습니다.");
    }
}
