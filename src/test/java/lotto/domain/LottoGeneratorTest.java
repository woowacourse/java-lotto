package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {
    @Test
    void generate_LottoNumber_count_6() {
        assertThat(new LottoGenerator().generate().size()).isEqualTo(6);
    }
}