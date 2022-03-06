package lotto.domain.generator;

import lotto.domain.lottonumber.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRandomGeneratorTest {
    @Test
    @DisplayName("이미 생성된 로또들과 로또 총 생성 숫자를 입력받아, 생성된 숫자들을 포함하여 총 생성 숫자 값만큼의 로또들을 생성해 반환한다.")
    void generate_Test() {
        //given
        final Lotto firstDefaultLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        final Lotto secondDefaultLotto = new Lotto(Arrays.asList(2, 3, 4, 5, 6, 7));
        final List<Lotto> defaultLottos = new ArrayList<>(Arrays.asList(firstDefaultLotto, secondDefaultLotto));
        final int numberOfGenerating = 6;
        final LottoRandomGenerator lottoRandomGenerator = new LottoRandomGenerator();
        //when
        final List<Lotto> generated =
                lottoRandomGenerator.generateLottosExceptDefaultLottos(numberOfGenerating, defaultLottos);
        final int actualDistinctCount = (int) generated.stream()
                .distinct()
                .count();
        //then
        assertThat(generated).contains(firstDefaultLotto).contains(secondDefaultLotto);
        assertThat(actualDistinctCount).isEqualTo(numberOfGenerating);
    }
}
