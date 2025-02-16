package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.Lotto.MIN_LOTTO_NUMBER;
import static lotto.Lotto.MAX_LOTTO_NUMBER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoMachineTest {
    @DisplayName("로또는 6개의 번호를 가진다")
    @Test
    void 로또는_6개의_번호를_가진다() {
        Lotto result = LottoMachine.createLotto();
        assertThat(result.getNumbers().size()).isEqualTo(6);
        result.getNumbers()
                .forEach(number -> assertThat(number).isBetween(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
    }
}
