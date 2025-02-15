package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoMachineTest {
    @DisplayName("구입 장수에 해당하는 개수의 로또를 발행한다")
    @Test
    void 구입_장수에_해당하는_개수의_로또를_발행한다() {
        assertThat(LottoMachine.issueLottos(5).size()).isEqualTo(5);
    }
}
