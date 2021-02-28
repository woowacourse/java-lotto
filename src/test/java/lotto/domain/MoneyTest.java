package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.EnumMap;
import java.util.Map;
import lotto.utils.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("실패 - 돈이 정수가 아닌 경우")
    void generate() {
        assertThatThrownBy(() -> Money.valueOf("3100.1"))
            .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("실패 - 돈이 숫자가 아닌 경우")
    void generate1() {
        assertThatThrownBy(() -> Money.valueOf("12ls"))
            .isInstanceOf(CustomException.class);
    }


    @Test
    @DisplayName("2000원 구매시, 1등 2개 수익률")
    void getEarningRate1() {
        final Money money = Money.valueOf("2000");

        Map<Rank, Integer> results = new EnumMap<>(Rank.class);
        results.put(Rank.FIRST_PLACE, 2);

        BigInteger earningRate = calculateEarningRate(Rank.FIRST_PLACE, results,
            BigDecimal.valueOf(2000));
        assertThat(money.getEarningRate(results)).isEqualTo(earningRate);
    }

    @Test
    @DisplayName("2장 구매시, 미당첨 수익률")
    void getEarningRate3() {
        final Money money = Money.valueOf("2000");

        Map<Rank, Integer> results = new EnumMap<>(Rank.class);
        results.put(Rank.UNRANKED, 2);

        assertThat(money.getEarningRate(results)).isEqualTo(0);
    }

    @Test
    @DisplayName("5장 구매시, 5등 1개 당첨시 수익률")
    void getEarningRate4() {
        final Money money = Money.valueOf("5000");

        Map<Rank, Integer> results = new EnumMap<>(Rank.class);
        results.put(Rank.FIFTH_PLACE, 1);
        results.put(Rank.UNRANKED, 4);

        assertThat(money.getEarningRate(results)).isEqualTo(100);
    }

    private BigInteger calculateEarningRate(Rank rankStatus, Map<Rank, Integer> results,
        BigDecimal buyPrice) {
        return rankStatus.getPrize()
            .multiply(BigDecimal.valueOf(results.get(rankStatus)))
            .divide(buyPrice, 2, BigDecimal.ROUND_CEILING)
            .multiply(BigDecimal.valueOf(100))
            .toBigInteger();
    }

}