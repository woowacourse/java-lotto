package lotto.domain.generator;

import static org.assertj.core.api.Assertions.*;
import java.util.List;
import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AutoLottoGeneratorTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 11})
    @DisplayName("로또 구입 개수로 자동 로또를 생성한다.")
    void createAutoLotto(int amount) {
        AutoLottoGenerator autoLottoGenerator = new AutoLottoGenerator(amount);
        List<Lotto> lottos = autoLottoGenerator.generateLottos();

        assertThat(lottos).hasSize(amount);
    }

    @Test
    @DisplayName("로또 구입 개수가 음수일 경우 예외를 발생한다.")
    void throwExceptionNegativeAmount() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new AutoLottoGenerator(-1))
            .withMessage("음수로 자동 로또를 생성할 수 없다.");
    }

}