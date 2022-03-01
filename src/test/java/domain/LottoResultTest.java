package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {

    @DisplayName("당첨 개수 확인")
    @ParameterizedTest
    @EnumSource(value = Rank.class, names = {"FIRST", "FIFTH", "FOURTH", "THIRD", "SECOND"})
    void count_ranks(Rank rank) {
        List<Rank> lottoRanks = Arrays.asList(Rank.values());
        LottoResult lottoResult = new LottoResult(lottoRanks);
        Map<Rank, Integer> rankCounts = lottoResult.countRank();
        assertThat(rankCounts.get(rank)).isEqualTo(1);
    }

    @DisplayName("수익률 계산")
    @Test
    void calculate_profit_rate() {
        List<Rank> lottoRanks = Arrays.asList(Rank.NOTHING, Rank.FIFTH);
        LottoResult lottoResult = new LottoResult(lottoRanks);

        assertThat(lottoResult.calculateProfitRate(new Payment(2000))).isEqualTo((double) 5000 / 2000);
    }
}
