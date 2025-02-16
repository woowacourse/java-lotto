package domain;

import static org.junit.jupiter.api.Assertions.*;

import constant.LottoConstants;
import java.util.Arrays;
import java.util.EnumMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PrizeResultTest {

    private EnumMap<Rank, Integer> result;
    private int lottoCount;

    @BeforeEach
    void beforeEach() {
        result = new EnumMap<>(Rank.class);
        result.put(Rank.RANK1, 1);
        result.put(Rank.RANK3, 5);
        result.put(Rank.RANK2, 5);

        lottoCount = 3;
    }

    @Test
    @DisplayName("로또 결과의 수익률이 제대로 계산되는지 확인한다.")
    void lotto_profit_calculate() {
        PrizeResult prizeResult = new PrizeResult(result, lottoCount);
        long prizeSum = Arrays.stream(Rank.values())
                .filter(rank -> !rank.isMiss())
                .mapToLong(rank -> (long) result.getOrDefault(rank, 0) * rank.getPrize())
                .sum();
        double expected = (double) prizeSum / (lottoCount * LottoConstants.LOTTO_PRICE);


        Assertions.assertThat(prizeResult.calculateProfit())
                .isEqualTo(expected);
    }
}