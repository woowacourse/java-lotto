package utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoNumberGeneratorTest {

    @Test
    void 랜덤_여섯개_들어왔는지_확인() {
        assertThat(LottoNumberGenerator.generate().size()).isEqualTo(6);
    }
}