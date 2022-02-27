package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import lotto.model.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @DisplayName("같은 숫자 리스트로 생성된 로또여도, 다른 값을 갖는다")
    @Test
    void generate_two_lottos() {
        Lotto lotto1 = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(Arrays.asList(6, 4, 1, 5, 2, 3));

        assertThat(lotto1).isNotEqualTo(lotto2);
    }

    @DisplayName("6개의 숫자를 생성한다")
    @Test
    void generate_six_number() {
        Lotto lotto = new Lotto(Lotto.selectNumbers());

        assertThat(lotto.getNumbers().size()).isEqualTo(6);
    }

    @DisplayName("6개의 숫자가 오름차순으로 정렬된다")
    @Test
    void sort_ascending() {
        Lotto lotto = new Lotto(Lotto.selectNumbers());
        List<Integer> numbers = lotto.getNumbers();
        for (int index = 0; index < (numbers.size() - 1); index++) {
            assertThat(numbers.get(index) < numbers.get(index + 1)).isTrue();
        }
    }
}
