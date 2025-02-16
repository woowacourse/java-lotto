package model;

import static org.assertj.core.api.Assertions.assertThat;

import constant.WinLottoInfo;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {
    @Test
    @DisplayName("로또 당첨 개수 계산 테스트")
    public void getCountTest() {
        //given
        List<LottoNumbers> lottoNumbers = new ArrayList<>();
        lottoNumbers.add(new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        WinLotto winLotto = new WinLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Result result = new Result(lottoNumbers, winLotto);
        // when
        int count = result.getCount(WinLottoInfo.FIRST);
        // then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("총 수익률 계산 테스트")
    public void totalReturnTest() {
        //given
        List<LottoNumbers> lottoNumbers = new ArrayList<>();
        lottoNumbers.add(new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        WinLotto winLotto = new WinLotto(List.of(1, 2, 3, 10, 8, 9), 7);
        Result result = new Result(lottoNumbers, winLotto);
        // when
        Double totalReturn = result.totalReturn(25000);
        // then
        assertThat(totalReturn).isEqualTo(0.2);
    }
}
