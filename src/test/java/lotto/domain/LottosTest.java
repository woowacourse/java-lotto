package lotto.domain;

import lotto.utils.LottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {


    @DisplayName("LottoGenerator를 이용한 Lottos 생성 테스트")
    @Test
    void testCreateLottos() {
        LottoGenerator fixedGenerator = new FixedGenerator();

        Lottos fixedLottos = new Lottos(Arrays.asList(fixedGenerator.generate()));

        assertThat(fixedLottos).isEqualTo(new Lottos(Arrays.asList(fixedGenerator.generate())));
    }
}
