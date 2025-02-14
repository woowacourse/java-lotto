import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottosTest {
    @Test
    void 여러_로또가_저장되는지_테스트() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 41, 5, 6);
        List<Integer> numbers3 = Arrays.asList(1, 2, 31, 4, 5, 6);
        List<Integer> numbers4 = Arrays.asList(1, 21, 3, 4, 5, 6);

        Lottos lottos = new Lottos();

        lottos.addLotto(new Lotto(numbers1));
        lottos.addLotto(new Lotto(numbers2));
        lottos.addLotto(new Lotto(numbers3));
        lottos.addLotto(new Lotto(numbers4));

        assertThat(lottos.getLottos().size()).isEqualTo(4);
    }

}
