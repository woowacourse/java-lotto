package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    @DisplayName("Lottos를 생성하는 경우")
    void createLottos() {
        Lotto lotto = LottoFactory.createLotto(new RandomLottoNumberGenerator());
        Lottos lottos = new Lottos(List.of(lotto));

        assertThat(lottos).isNotNull();
    }

    @Test
    @DisplayName("보유하고 있는 로또들과 당첨 로또의 매칭 결과를 계산 1")
    void calculateLottoMatchResult_01() {
        List<LottoReward> lottoRewards = getLottoRewards();

        assertThat(lottoRewards).containsSequence(LottoReward.FIRST);
    }

    @Test
    @DisplayName("보유하고 있는 로또들과 당첨 로또의 매칭 결과를 계산 2")
    void calculateLottoMatchResult_02() {
        List<LottoReward> lottoRewards = getLottoRewards();

        assertThat(lottoRewards).containsSequence(LottoReward.FIFTH);
    }

    private List<LottoReward> getLottoRewards() {
        Lotto lotto = LottoFactory.createLotto(Arrays.asList(12, 23, 6, 44, 17, 16));

        Lotto lotto1 = LottoFactory.createLotto(List.of(12, 23, 6, 44, 17, 16));
        Lotto lotto2 = LottoFactory.createLotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto3 = LottoFactory.createLotto(List.of(12, 23, 6, 13, 7, 43));

        Lottos lottos = new Lottos(List.of(lotto1, lotto2, lotto3));
        WinningLotto winningLotto = new WinningLotto(lotto, LottoNumber.valueOf(2));

        return lottos.match(winningLotto);
    }
}

