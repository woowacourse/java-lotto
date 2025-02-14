package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    @DisplayName("6개의 숫자를 갖는 로또를 발급할 수 있다.")
    @Test
    void countOfIssuedLottoNumbers() {
        Lotto lotto = LottoMachine.issue();
        int countOfIssuedLottoNumbers = lotto.getNumbers().size();

        assertThat(countOfIssuedLottoNumbers).isEqualTo(6);
    }

    @DisplayName("로또 번호는 중복되지 않는다.")
    @Test
    void lottoNumbersAreDistinct() {
        Lotto lotto = LottoMachine.issue();
        int countOfIssuedLottoNumbers = lotto.getNumbers().size();
        int distinctLottoNumberCount = (int) lotto.getNumbers()
                .stream()
                .distinct()
                .count();

        assertThat(countOfIssuedLottoNumbers).isEqualTo(distinctLottoNumberCount);
    }
}
