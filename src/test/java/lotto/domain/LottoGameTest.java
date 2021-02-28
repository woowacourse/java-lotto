package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoGameTest {

    @DisplayName("돈에 따른 자동 로또 구입 개수 테스트")
    @Test
    void testAutoBuyLottos() {
        LottoGame lottoGame = new LottoGame();

        lottoGame.buyAutoLottos(4);

        assertThat(lottoGame.toAutoLottos().toList().size()).isEqualTo(4);
    }

    @DisplayName("수동 로또 구입 개수 테스트")
    @Test
    void testBuyManualLottos() {
        LottoGame lottoGame = new LottoGame();
        FixedGenerator fixedGenerator = new FixedGenerator();

        lottoGame.buyManualLottos(new Lottos(Arrays.asList(fixedGenerator.generate())));

        assertThat(lottoGame.toManualLottos().toList().get(0)).isEqualTo(fixedGenerator.generate());
    }
}