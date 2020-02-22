package lotto.domain.result;

import lotto.domain.result.win.rank.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lotto.domain.result.win.rank.Rank.FIFTH;
import static lotto.domain.result.win.rank.Rank.SIXTH;
import static org.assertj.core.api.Assertions.assertThat;

class LottoResultBundleTest {

    @DisplayName("상금결과로 통계결과 만들기")
    @Test
    void getPrizeCount() {
        //given
        List<Rank> ranks = Arrays.asList(FIFTH, FIFTH, FIFTH, FIFTH, FIFTH, FIFTH, SIXTH, SIXTH, SIXTH, SIXTH);
        String expect = "300%";

        //when
        LottoResultBundle lottoResultBundle = new LottoResultBundle(ranks);
        String result = lottoResultBundle.getRate();

        //then
        assertThat(result).isEqualTo(expect);
    }
}
