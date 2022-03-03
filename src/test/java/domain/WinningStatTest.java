package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class WinningStatTest {

    @Test
    @DisplayName("로또 수익률 검증")
    void validateProfit() {
        Map<LottoRank, Integer> rankInfo = new HashMap<>();
        Arrays.stream(LottoRank.values())
                .forEach(r -> rankInfo.put(r, 0));
        rankInfo.put(LottoRank.FIFTH, 1);
        rankInfo.put(LottoRank.NOTHING, 13);
        WinningStat winningStat = new WinningStat(rankInfo);
        double profit = winningStat.calculateProfit(1000);

        assertThat(profit).isEqualTo((double) 5000 / 14000);
    }
}
