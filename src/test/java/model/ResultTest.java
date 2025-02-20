package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {
    @Test
    @DisplayName("로또 당첨 개수 계산 테스트")
    public void getCountTest() {
        // given
        List<WinLottoInfo> winResults = List.of(WinLottoInfo.FIRST);
        Result result = new Result(winResults);

        // when
        int count = result.getCount(WinLottoInfo.FIRST);

        // then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("총 수익률 계산 테스트")
    public void totalReturnTest() {
        // given
        List<WinLottoInfo> winResults = List.of(WinLottoInfo.FIFTH);
        Result result = new Result(winResults);

        // when
        Double totalReturn = result.totalReturn(25000);

        // then
        assertThat(totalReturn).isEqualTo(0.2);
    }

    @Test
    @DisplayName("당첨이 없는 경우 개수 테스트")
    public void getCountNoneTest() {
        // given
        List<WinLottoInfo> winResults = List.of(WinLottoInfo.NONE);
        Result result = new Result(winResults);

        // when
        int count = result.getCount(WinLottoInfo.NONE);

        // then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("당첨이 여러 개일 때 개수 테스트")
    public void getMultipleWinsTest() {
        // given
        List<WinLottoInfo> winResults = List.of(WinLottoInfo.FIRST, WinLottoInfo.FIFTH, WinLottoInfo.FIFTH);
        Result result = new Result(winResults);

        // when
        int firstCount = result.getCount(WinLottoInfo.FIRST);
        int fifthCount = result.getCount(WinLottoInfo.FIFTH);

        // then
        assertThat(firstCount).isEqualTo(1);
        assertThat(fifthCount).isEqualTo(2);
    }
}
