package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {
    @Test
    void 로또_생성_확인() {
        assertThat(LottoGenerator.create()).isInstanceOf(Lotto.class);
        System.out.println(LottoGenerator.create());
    }
}