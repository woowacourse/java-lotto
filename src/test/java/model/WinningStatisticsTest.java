package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningStatisticsTest {

    @Test
    @DisplayName("로또 당첨 결과로 수익률을 반환한다.")
    void 로또_당첨_결과로_수익률을_반환한다() {
        Lotto lotto = new Lotto(Set.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(6);
        // 5개 + 보너스 볼 -> 2등
        UserLotto userLotto = new UserLotto(Set.of(1, 2, 3, 4, 5, 45), bonusNumber);
        Wallet wallet = new Wallet(10000);

        LottoRepository lottoRepository = new LottoRepository();
        lottoRepository.addLotto(lotto);

        WinningStatistics winningStatistics = new WinningStatistics(lottoRepository, userLotto);

        // 2등 상금(30_000_000) / 구매 금액(10_000) => 3000
        assertThat(winningStatistics.calculateWinningRate(wallet)).isEqualTo(3000);
    }


}
