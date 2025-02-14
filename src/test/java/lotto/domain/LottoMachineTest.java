package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoMachineTest {
    @DisplayName("로또는 6개의 번호를 가진다")
    @Test
    void 로또는_6개의_번호를_가진다() {
        Lotto result = LottoMachine.createLotto();
        assertThat(result.getNumbers().size()).isEqualTo(6);
    }

    @DisplayName("구입 장수에 해당하는 개수의 로또를 발행한다")
    @Test
    void 구입_장수에_해당하는_개수의_로또를_발행한다() {
        assertThat(LottoMachine.issueLottos(5).size()).isEqualTo(5);
    }
}
