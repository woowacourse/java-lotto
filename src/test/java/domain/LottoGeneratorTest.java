package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

class LottoGeneratorTest {
    @DisplayName("6개의 숫자를 중복 없이 생성한다.")
    @RepeatedTest(10)
    void duplicateTest() {
        final List<LottoNumber> generatedNumbers = LottoGenerator.generate();

        assertThat(generatedNumbers).hasSize(6).doesNotHaveDuplicates();
    }
}
