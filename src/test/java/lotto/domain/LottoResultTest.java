package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LottoResultTest {
    LottoResult lottoResult = new LottoResult(WinningLotto.create(Arrays.asList(1,2,3,4,5,6)));

    @Test
    void 통계정보_확인1() {
        lottoResult.update(LottoTicket.create(Arrays.asList(1, 2, 3, 8, 9, 10)));
        assertThat(lottoResult.countOfRank(LottoRank.rankOf(3))).isEqualTo(1);
    }

    @Test
    void 통계정보_확인2() {
        assertThat(lottoResult.countOfRank(LottoRank.rankOf(2))).isEqualTo(0);
    }
}
