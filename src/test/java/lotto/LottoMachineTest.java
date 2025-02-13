package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {
    @DisplayName("로또는 6개의 번호를 가진다")
    @Test
    void 로또는_6개의_번호를_가진다() {
        Lotto result = LottoMachine.createLotto();
        assertThat(result.getNumbers().size()).isEqualTo(6);
    }
}