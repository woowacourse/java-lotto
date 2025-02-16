package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.mock.TestLottoNumberGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.domain.Lotto.MIN_LOTTO_NUMBER;
import static lotto.domain.Lotto.MAX_LOTTO_NUMBER;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoMachineTest {
    private final LottoMachine lottoMachine = new LottoMachine(new TestLottoNumberGenerator());

    @DisplayName("구입금액이 1000원으로 나누어 떨어지지 않으면 예외를 던진다")
    @Test
    void 구입금액이_1000원으로_나누어_떨어지지_않으면_예외를_던진다() {
        assertThatThrownBy(() ->
                lottoMachine.purchase(500)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입금액은 %d원으로 나누어져야 합니다. [입력: %d]".formatted(LottoMachine.LOTTO_UNIT_PRICE, 500));
    }

    @DisplayName("구입금액이 양수가 아니라면 예외를 던진다")
    @Test
    void 구입금액이_양수가_아니라면_예외를_던진다() {
        assertThatThrownBy(() ->
                lottoMachine.purchase(-1000)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입금액은 양수여야 합니다. [입력: %d]".formatted(-1000));
    }

    @DisplayName("구입금액이 10만원을 초과하면 예외를 던진다")
    @Test
    void 구입금액이_10만원을_초과하면_예외를_던진다() {
        assertThatThrownBy(() ->
                lottoMachine.purchase(200000)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입금액은 최대 100000원까지입니다. [입력: %d]".formatted(200000));
    }

    @DisplayName("구입금액에 해당하는 개수의 로또를 발행한다")
    @Test
    void 구입금액에_해당하는_개수의_로또를_발행한다() {
        Assertions.assertThat(lottoMachine.purchase(5000).size()).isEqualTo(5);
    }

    @DisplayName("발행된 로또는 적정 범위에서 6개의 고유한 번호를 가진다")
    @Test
    void 발행된_로또는_적정_범위에서_6개의_고유한_번호를_가진다() {
        LottoMachine lottoMachine = new LottoMachine(new TestLottoNumberGenerator());
        Lotto result = lottoMachine.purchase(1000).get(0);
        assertThat(result.getNumbers().size()).isEqualTo(6);
        result.getNumbers()
                .forEach(number -> assertThat(number).isBetween(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
    }
}
