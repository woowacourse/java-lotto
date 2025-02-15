package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumbersGeneratorTest {
    @DisplayName("생성된 번호들의 개수는 6개이다")
    @Test
    void 생성된_번호들의_개수는_6개이다() {
        assertThat(NumbersGenerator.generateLottoNumbers()).hasSize(6);
    }

    @DisplayName("생성된 번호들은 서로 중복되지 않는다")
    @Test
    void 생성된_번호들은_서로_중복되지_않는다() {
        assertThat(NumbersGenerator.generateLottoNumbers()).doesNotHaveDuplicates();
    }
}
