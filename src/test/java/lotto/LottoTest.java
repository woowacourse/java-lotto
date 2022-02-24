package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.model.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @DisplayName("6개의 숫자를 생성한다")
    @Test
    void generate_six_number() {
        Lotto lotto = new Lotto();

        assertThat(lotto.getNumbers().size()).isEqualTo(6);
    }

    @DisplayName("6개의 숫자가 오름차순으로 정렬된다")
    @Test
    void sort_ascending() {
        Lotto lotto = new Lotto();
        List<Integer> numbers = lotto.getNumbers();
        for (int index = 0; index < (numbers.size() - 1); index++) {
            assertThat(numbers.get(index) < numbers.get(index + 1)).isTrue();
        }
    }
}
