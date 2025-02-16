import model.Lotto;
import model.Lottos;
import model.Numbers;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottosTest {
    @Test
    void 여러_로또가_저장되는지_테스트() {
        //given
        Numbers numbers1 = new Numbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        Numbers numbers2 = new Numbers(Arrays.asList(1, 2, 3, 41, 5, 6));
        Numbers numbers3 = new Numbers(Arrays.asList(1, 2, 31, 4, 5, 6));
        Numbers numbers4 = new Numbers(Arrays.asList(1, 21, 3, 4, 5, 6));

        Lottos lottos = new Lottos();

        lottos.addLotto(new Lotto(numbers1));
        lottos.addLotto(new Lotto(numbers2));
        lottos.addLotto(new Lotto(numbers3));
        lottos.addLotto(new Lotto(numbers4));
        //when & then
        assertThat(lottos.getLottos().size()).isEqualTo(4);
    }

}
