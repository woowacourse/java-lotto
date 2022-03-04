package lotto.domain.generator;

import lotto.domain.lottonumber.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRandomGeneratorTest {
    @Test
    @DisplayName("사용자 입력 로또 번호들과 숫자 값을 입력받아, 사용자 입력 숫자들을 포함하여 숫자 값만큼의 로또들을 생성해 반환한다.")
    void generate_Test() {
        //given
        final Lotto firstUserInputLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        final Lotto secondUserInputLotto = new Lotto(Arrays.asList(2, 3, 4, 5, 6, 7));
        final List<Lotto> userInputLottos = Arrays.asList(firstUserInputLotto, secondUserInputLotto);
        final int numberOfGenerating = 6;
        final LottoRandomGenerator lottoRandomGenerator = new LottoRandomGenerator();
        //when
        final List<Lotto> generated =
                lottoRandomGenerator.generateLottosExceptManualGenerated(numberOfGenerating, userInputLottos);
        final int actualDistinctCount = (int) generated.stream()
                .distinct()
                .count();
        //then
        assertThat(generated).contains(firstUserInputLotto).contains(secondUserInputLotto);
        assertThat(actualDistinctCount).isEqualTo(numberOfGenerating);
    }
}
