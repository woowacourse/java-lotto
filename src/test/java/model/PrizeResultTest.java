package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.EnumMap;
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
        result = new EnumMap<>(Rank.class);
        result.put(Rank.RANK1, 0);
        result.put(Rank.RANK2, 0);
        result.put(Rank.RANK3, 0);
        result.put(Rank.RANK4, 2);
        result.put(Rank.RANK5, 1);
        result.put(Rank.MISS, 2);

        lottoCount = 5;
    }

    @Test
    @DisplayName("calculateProfit메서드_테스트")
    void calculateProfit메서드_테스트() {
        PrizeResult prizeResult = new PrizeResult(result, lottoCount);

        assertEquals(21, prizeResult.calculateProfit());
    }
}