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
    @DisplayName("수동 로또 구매를 구입 금액보다 많이할 시 예외를 던진다")
    void validatePositiveManualLottoSizeTest() {
        Money money = new Money(12000);
        LottoSize manualLottoSize = new LottoSize(3);
        int actual = manualLottoSize.getAutoLottoSize(money);

        assertThat(actual).isEqualTo(9);
    }

    @Test
    @DisplayName("수동 로또 구매를 구입 금액보다 많이할 시 예외를 던진다")
    void getAutoLottoSizeTest() {
        assertThatThrownBy(() -> {
            Money money = new Money(1000);
            LottoSize manualLottoSize = new LottoSize(2);
            manualLottoSize.getAutoLottoSize(money);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또는 0장 이상 구매하셔야 합니다.");
    }
}
