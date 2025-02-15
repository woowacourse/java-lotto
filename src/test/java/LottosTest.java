import domain.Lotto;
import domain.Lottos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottosTest {

    @Test
    @DisplayName("여러 로또를 저장할 수 있다.")
    void 여러_로또를_저장할_수_있다() {
        // given
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 41, 5, 6);
        List<Integer> numbers3 = Arrays.asList(1, 2, 31, 4, 5, 6);
        List<Integer> numbers4 = Arrays.asList(1, 21, 3, 4, 5, 6);
        Lottos lottos = new Lottos();

        // when
        lottos.addLotto(new Lotto(numbers1));
        lottos.addLotto(new Lotto(numbers2));
        lottos.addLotto(new Lotto(numbers3));
        lottos.addLotto(new Lotto(numbers4));

        // then
        assertThat(lottos.getLottoCount())
                .isEqualTo(4);
    }
}
