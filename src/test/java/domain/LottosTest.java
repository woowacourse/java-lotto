package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    @DisplayName("Lottos를 생성하는 경우")
    void createLottos() {
        Lotto lotto = LottoFactory.createLotto(new RandomLottoNumbersGenerator());
        Lottos lottos = new Lottos(List.of(lotto));

        assertThat(lottos).isNotNull();
    }

    @Test
    @DisplayName("Lottos 객체를 null 로 생성하려는 경우")
    void createLottosWithNull() {
        assertThatThrownBy(() ->
            new Lottos(null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Lottos 객체를 null 로 생성하려는 경우")
    void createLottosWithEmpty() {
        assertThatThrownBy(() ->
            new Lottos(new ArrayList<>()))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보유하고 있는 로또들과 당첨 로또의 매칭 결과를 계산 1")
    void calculateLottoMatchResult_01() {
        Map<LottoReward, Integer> winningStatistics = getLottoWinningStatistics().values();

        assertThat(winningStatistics.get(LottoReward.FIRST)).isEqualTo(1);
    }

    @Test
    @DisplayName("보유하고 있는 로또들과 당첨 로또의 매칭 결과를 계산 2")
    void calculateLottoMatchResult_02() {
        Map<LottoReward, Integer> winningStatistics = getLottoWinningStatistics().values();

        assertThat(winningStatistics.get(LottoReward.FIFTH)).isEqualTo(1);
    }

    private WinningStatistics getLottoWinningStatistics() {
        LottoGameMoney money = new LottoGameMoney(3000);
        Lotto lotto = LottoFactory.createLotto(Arrays.asList(12, 23, 6, 44, 17, 16));

        Lotto lotto1 = LottoFactory.createLotto(List.of(12, 23, 6, 44, 17, 16));
        Lotto lotto2 = LottoFactory.createLotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto3 = LottoFactory.createLotto(List.of(12, 23, 6, 13, 7, 43));

        Lottos lottos = new Lottos(List.of(lotto1, lotto2, lotto3));
        WinningLotto winningLotto = new WinningLotto(lotto, LottoNumber.valueOf(2));

        return lottos.match(money, winningLotto);
    }
}

