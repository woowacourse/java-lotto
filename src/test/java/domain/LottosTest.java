package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class LottosTest {

    @Test
    @DisplayName("Lottos를 생성하는 경우")
    void createLottos() {
        List<Lotto> lottos = List.of(LottoFactory.createLotto(List.of(1, 2, 3, 4, 5, 6)));

        assertThat(new Lottos(lottos)).isNotNull();
    }

    @Test
    @DisplayName("보유하고 있는 로또들과 당첨 로또의 매칭 결과를 계산")
    void calculateLottoMatchResult() {
        WinningLotto winningLotto = new WinningLotto(LottoFactory.createLotto(
            List.of(1, 2, 3, 4, 5, 6)),
            LottoNumber.valueOf(7));

        Lottos lottos = new Lottos(List.of(LottoFactory.createLotto(List.of(1, 2, 3, 4, 5, 6))));

        List<LottoReward> lottoRewards = lottos.calculateLottoReward(winningLotto);
        lottoRewards.forEach(lottoReward -> assertThat(lottoReward).isEqualTo(LottoReward.FIRST));
    }
}
