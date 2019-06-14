package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class LottoManualGeneratorTest {
    @Test
    void 수동_로또_생성기_생성() {
        assertThat(new LottoNoManualGenerator(Arrays.asList(1, 2, 3, 4, 5, 6)))
                .isEqualTo(new LottoNoManualGenerator(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void 수동_로또_생성_테스트() {
        assertThat(new LottoNoManualGenerator(Arrays.asList(1, 2, 3, 4, 5, 6)).generate())
                .isEqualTo(6);
    }

    @Test
    void 수동_로또_크기_테스트() {
        assertThat(new LottoNoManualGenerator(Arrays.asList(1, 2, 3, 4, 5, 6)).size())
                .isEqualTo(6);
    }
}