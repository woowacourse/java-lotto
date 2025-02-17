package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoStatisticsTest {
    @Test
    @DisplayName("로또 당첨 개수 계산 테스트")
    public void getCountTest() {
        //given
        List<Lotto> lottoNumbers = new ArrayList<>();
        lottoNumbers.add(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        WinLotto winLotto = new WinLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        LottoStatistics lottoStatistics = new LottoStatistics(lottoNumbers, winLotto);
        // when
        Integer count = lottoStatistics.getCount(LottoWinRank.FIRST);
        // then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("총 수익률 계산 테스트")
    public void totalReturnTest() {
        //given
        List<Lotto> lottoNumbers = new ArrayList<>();
        lottoNumbers.add(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        WinLotto winLotto = new WinLotto(List.of(1, 2, 3, 10, 8, 9), 7);
        LottoStatistics lottoStatistics = new LottoStatistics(lottoNumbers, winLotto);
        // when
        Double totalReturn = lottoStatistics.totalReturn(25000);
        // then
        assertThat(totalReturn).isEqualTo(0.2);
    }
}
