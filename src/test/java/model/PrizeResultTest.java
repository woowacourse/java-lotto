package model;

import static common.constant.NumberConstants.LOTTO_PRICE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.EnumMap;
import java.util.Map;
import model.result.PrizeResult;
import model.result.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrizeResultTest {
    private EnumMap<Rank, Integer> result;
    private int lottoCount;

    @BeforeEach
    void setUp() {
        result = new EnumMap<>(Map.of(
                Rank.RANK1, 0,
                Rank.RANK2, 0,
                Rank.RANK3, 0,
                Rank.RANK4, 2,
                Rank.RANK5, 1,
                Rank.MISS, 2
        ));

        lottoCount = 5;
    }

    @Test
    @DisplayName("calculateProfit메서드_테스트")
    void calculateProfit메서드_테스트() {
        PrizeResult prizeResult = new PrizeResult(result, lottoCount);

        // result 결과: 4등 2회, 5등 1회 당첨
        long sum = Rank.RANK4.getPrice() * 2L + Rank.RANK5.getPrice();
        double profit = (double) sum / (lottoCount * LOTTO_PRICE);

        assertEquals(profit, prizeResult.calculateProfit());
    }
}