package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.model.number.LottoNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
    @DisplayName("6개의 숫자를 생성한다")
    @Test
    void generate_six_number() {
        Lotto lotto = new Lotto(LottoNumbers.ofRandomNumbers());

        assertThat(lotto.getNumbers().getSize()).isEqualTo(6);
    }

    @DisplayName("6개의 숫자가 오름차순으로 정렬된다")
    @Test
    void sort_ascending() {
        Lotto lotto = new Lotto(LottoNumbers.ofRandomNumbers());
        LottoNumbers numbers = lotto.getNumbers();

        for (int index = 0; index < (numbers.getSize() - 1); index++) {
            assertThat(numbers.get(index).compareTo(numbers.get(index + 1))).isEqualTo(-1);
        }
    }
}
