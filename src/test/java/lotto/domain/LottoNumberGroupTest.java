package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoNumberGroupTest {
    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 로또_번호로_변환_테스트() {
        Assertions.assertThat(LottoNumberGroup.parseLottoNumberGroup(1))
                .isEqualTo(LottoNumberGroup.ONE);
    }
}
