package domain.generator;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import org.junit.jupiter.api.Test;

class AutoLottoGeneratorTest {
    @Test
    void auto_lotto_generator_generate() {
        final AutoLottoGenerator autoLottoGenerator = new AutoLottoGenerator();

        final Lotto autoLotto = autoLottoGenerator.generate();

        assertThat(autoLotto.get().size()).isEqualTo(6);
    }
}
