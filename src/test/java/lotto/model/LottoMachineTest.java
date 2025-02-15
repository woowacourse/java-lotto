package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    @DisplayName("6개의 숫자를 갖는 로또를 발급할 수 있다.")
    @Test
    void ok_LottoNumberCount() {
        Lotto lotto = LottoMachine.issue();

        assertThat(lotto.getLottoNumbers().size()).isEqualTo(6);
    }

    @DisplayName("중복되지 않는 숫자들을 갖는 로또를 발급할 수 있다.")
    @Test
    void ok_DistinctNumbers() {
        Lotto lotto = LottoMachine.issue();

        assertThat(lotto.getLottoNumbers()
                .stream()
                .distinct()
                .count()
        ).isEqualTo(lotto.getLottoNumbers().size());
    }
}
