package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static domain.LottoMachine.LOTTO_PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoMachineTest {

    private final LottoMachine lottoMachine = new LottoMachine(
            new LottoNumberGenerator(
                    new SecureRandom()));

    @Test
    @DisplayName("로또머신은 올바르게 입력받은 돈으로 로또를 생성할 수 있다")
    void 로또머신은_올바르게_입력받은_돈으로_로또를_생성할_수_있다() {
        // given
        Money money = Money.from(LOTTO_PRICE * 10);
        int purchasedLottoCount = money.getAmount() / LOTTO_PRICE;

        // when
        int lottoCount = lottoMachine.buyLottos(money).getLottoCount();

        // then
        assertThat(lottoCount)
                .isEqualTo(purchasedLottoCount);
    }

    @Test
    @DisplayName("구입 금액이 로또 가격보다 작으면 예외가 발생한다")
    void 구입_금액이_로또_가격보다_작으면_예외가_발생한다() {
        // given
        Money money = Money.from((int) (LOTTO_PRICE * 0.333));

        // when
        // then
        assertThatThrownBy(() -> lottoMachine.buyLottos(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또는 최소" + LOTTO_PRICE + "원부터 구매할 수 있습니다");
    }

    @Test
    @DisplayName("구입 금액이 로또 가격의 배수가 아니면 예외가 발생한다")
    void 구입_금액이_로또_가격의_배수가_아니면_예외가_발생한다() {
        // given
        Money money = Money.from((int) (LOTTO_PRICE * 3.333));

        // when
        // then
        assertThatThrownBy(() -> lottoMachine.buyLottos(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구입 금액은 로또 가격 단위(" + LOTTO_PRICE + ")의 배수여야 합니다.");
    }
}
