package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoResultTest {
    @Test
    void 통계정보_확인1() {
        LottoResult lottoResult = new LottoResult(WinningLotto.create(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottoResult.update(LottoTicket.create(Arrays.asList(1, 2, 3, 8, 9, 10)));
        assertThat(lottoResult.countOfRank(LottoRank.rankOf(3))).isEqualTo(1);
    }

    @Test
    void 통계정보_확인2() {
        LottoResult lottoResult = new LottoResult(WinningLotto.create(Arrays.asList(1, 2, 3, 4, 5, 6)));
        assertThat(lottoResult.countOfRank(LottoRank.rankOf(2))).isEqualTo(0);
    }

    @Test
    void 수익률_확인() {
        LottoResult lottoResult = new LottoResult(WinningLotto.create(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottoResult.update(LottoTicket.create(Arrays.asList(1, 2, 3, 8, 9, 10)));
        lottoResult.update(LottoTicket.create(Arrays.asList(1, 2, 3, 7, 8, 9)));
        assertEquals(10000/20, lottoResult.earningRate(), 0.1);
    }
}
