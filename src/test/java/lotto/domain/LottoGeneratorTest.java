package lotto.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoGeneratorTest {

    @Test
    void 랜덤_번호를_가진_로또를_생성한다() {
        // Given
        LottoGenerator lottoGenerator = new LottoGenerator();

        // When
        List<LottoNumber> numbers = lottoGenerator.generate();

        // Then
        Assertions.assertThat(numbers.stream().distinct().count()).isEqualTo(6);
    }
}
