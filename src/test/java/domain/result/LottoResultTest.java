package domain.result;

import static domain.result.RankTest.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class LottoResultTest {

    @Test
    void testLottoResult() {
        List<Rank> ranks = getRanksFixture();
        LottoResult lottoResult = new LottoResult(ranks);
    }

    @Test
    void countRanks() {
        int count = 100000;
        List<Rank> ranks = getRanksFixture();
        LottoResult lottoResult = new LottoResult(ranks);

        assertThat(lottoResult.count(Rank.FIRST)).isEqualTo(count);
    }
}