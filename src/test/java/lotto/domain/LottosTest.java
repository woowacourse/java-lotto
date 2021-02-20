package lotto.domain;

import lotto.utils.LottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {


    @DisplayName("LottoGenerator를 이용한 Lottos 생성 테스트")
    @Test
    void testCreateLottos() {
        LottoGenerator fixedGenerator = new FixedGenerator();
        Money purchaseMoney = new Money("1000");

        Lottos lottos = new Lottos(purchaseMoney);
        Lotto fixedLotto = fixedGenerator.generate();

        assertThat(lottos.toList().get(0)).isEqualTo(fixedLotto);
    }
}
