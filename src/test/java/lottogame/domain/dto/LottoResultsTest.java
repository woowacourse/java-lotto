package lottogame.domain.dto;

import lottogame.domain.Money;
import lottogame.domain.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultsTest {
    private LottoResults lottoResult;

    @BeforeEach
    void setUp() {
        List<LottoResult> lottoResultGroup = Arrays.asList(
                new LottoResult(4, false),
                new LottoResult(3, false),
                new LottoResult(5, true),
                new LottoResult(5, false));
        lottoResult = new LottoResults(lottoResultGroup);
        lottoResult.makeStatistics(new Money(4000));
    }

    @DisplayName("로또 당첨 통계를 출력하기 위한 기능이 잘 수행되는 지 결과 비교")
    @Test
    void 로또_결과_계산() {
        Map<Rank, Integer> result = new LinkedHashMap<Rank, Integer>() {{
            put(Rank.FIFTH, 1);
            put(Rank.FOURTH, 1);
            put(Rank.THIRD, 1);
            put(Rank.SECOND, 1);
            put(Rank.FIRST, 0);
        }};
        assertThat(lottoResult.values()).isEqualTo(result);
    }

    @Test
    void 로또_수익률_계산() {
        float expected = (float) (50000 + 5000 + 30000000 + 1500000) / 4000;
        assertThat(lottoResult.getProfit()).isEqualTo(expected);
    }
}