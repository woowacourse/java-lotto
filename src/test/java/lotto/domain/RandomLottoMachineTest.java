package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RandomLottoMachineTest {

    @DisplayName("로또 번호 6개를 생성할 수 있다.")
    @Test
    void createRandomLottoNumbers() {
        final Set<Integer> randomLottoNumbers = RandomLottoMachine.createRandomLottoNumbers();

        assertThat(randomLottoNumbers.size()).isEqualTo(6);
    }
}
