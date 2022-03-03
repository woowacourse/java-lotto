package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoMachineTest {

    @DisplayName("요청하는 개수만큼 로또를 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void generateLottoNumbers(int size) {
        assertThat(LottoMachine.generateLottos(size)).hasSize(size);
    }
}