package lotto.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMoneyTest {

    @Test
    @DisplayName("로또 구입 금액 입력시 몇 장의 로또 생성하는지 확인")
    void generateLottosTest() {
        LottoMoney lottoMoney = new LottoMoney(14000);
        int numberOfLottos = lottoMoney.getLottoSize();

        assertThat(numberOfLottos).isEqualTo(14);
    }

    @Test
    @DisplayName("로또 구매 금액이 로또 가격으로 나눠떨어지지 않으면 예외를 던진다")
    void validateUnitPrice() {
        assertThatThrownBy(() -> {
            LottoMoney lottoMoney = new LottoMoney(14001);
            lottoMoney.getLottoSize();
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("거스름돈을 지급하지 않습니다. 금액이 남지 않게 지불해주세요.");
    }

    @Test
    @DisplayName("로또 구입 금액을 음수로 입력시 예외를 던진다")
    void validatePositiveTest() {
        assertThatThrownBy(() ->
            new LottoMoney(-14000))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("구입 금액은 양수여야 합니다.");
    }
}
