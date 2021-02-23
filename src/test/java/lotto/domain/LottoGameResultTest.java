package lotto.domain;

import lotto.utils.LottoGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGameResultTest {

    static LottoGameResult lottoGameResult = new LottoGameResult();


    @BeforeAll
    static void beforeAll() {
        lottoGameResult.add(Rank.rankOf(4, false)); // 4
        lottoGameResult.add(Rank.rankOf(3, false)); // 5
        lottoGameResult.add(Rank.rankOf(2, false));
        lottoGameResult.add(Rank.rankOf(1, false));
        lottoGameResult.add(Rank.rankOf(0, false));
    }

    @DisplayName("랭크 별 개수 테스트")
    @Test
    void testAdd() {
        assertThat(lottoGameResult.countByRank(Rank.FOURTH)).isEqualTo(1);
        assertThat(lottoGameResult.countByRank(Rank.NOTHING)).isEqualTo(3);
    }


    @DisplayName("수익률 계산 테스트")
    @Test
    void testCalculateProfit() {
        LottoGenerator fixedGenerator = new FixedGenerator();
        Lottos fixedLottos = new Lottos(Arrays.asList(fixedGenerator.generate()));

        Lotto manualLotto = new Lotto(Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)));

        Lottos manualLottos = new Lottos(Arrays.asList(manualLotto));

        WinningLotto winningLotto = new WinningLotto(fixedLottos.toList().get(0), 8);

        LottoGame lottoGame = new LottoGame();
        double profit = lottoGame.compareWithWinningLotto(manualLottos, fixedLottos, winningLotto).calculateProfit();

        assertThat(profit).isEqualTo(2_000_000);
    }
}
