package utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoBallGeneratorTest {

    @Test
    void 랜덤_여섯개_들어왔는지_확인() {
        assertThat(LottoRandomGenerator.generate().size()).isEqualTo(6);
    }
}