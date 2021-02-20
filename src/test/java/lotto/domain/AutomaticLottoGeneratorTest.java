package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AutomaticLottoGeneratorTest {
    @DisplayName("로또를 에러 없이 잘 생성 하는지")
    @Test
    void createLottos() {
        AutomaticLottoGenerator automaticLottoGenerator = new AutomaticLottoGenerator();

        assertThat(isLottoCreationSuccessful(automaticLottoGenerator)).isTrue();
    }

    private boolean isLottoCreationSuccessful(AutomaticLottoGenerator automaticLottoGenerator) {
        try {
            automaticLottoGenerator.createLotto();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}