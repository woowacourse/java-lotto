package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningStatisticsTest {
    private WinningStatistics winningStatistics;

    @BeforeEach
    void setUp() {
        Lotto lotto = new Lotto(new TestLottoNumberGenerator(List.of(1, 2, 3, 4, 5, 6)));

        // 5개 + 보너스 볼 -> 2등
        BonusNumber bonusNumber = new BonusNumber(6);
        UserLotto userLotto = new UserLotto(List.of(1, 2, 3, 4, 5, 45), bonusNumber);

        LottoRepository lottoRepository = new LottoRepository();
        lottoRepository.addLotto(lotto);

        winningStatistics = new WinningStatistics(lottoRepository, userLotto);
    }

    @Test
    @DisplayName("WinningStatistics가 로또 결과를 정상적으로 저장한다.")
    void WinningStatistics가_로또_결과를_정상적으로_저장한다() {
        Map<RankType, Integer> winningResult = winningStatistics.getWinningStatistics();

        assertThat(winningResult.get(RankType.SECOND)).isEqualTo(1);
        assertThat(winningResult.get(RankType.FIRST)).isEqualTo(0);
        assertThat(winningResult.get(RankType.THIRD)).isEqualTo(0);
    }

    @Test
    @DisplayName("로또 당첨 결과로 수익률을 반환한다.")
    void 로또_당첨_결과로_수익률을_반환한다() {
        Wallet wallet = new Wallet(10_000);

        // 2등 상금(30_000_000) / 구매 금액(10_000) => 3000
        assertThat(winningStatistics.calculateWinningRate(wallet)).isEqualTo(3_000);
    }


}
