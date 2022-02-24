package lotto.model.prize;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.model.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrizeInformationsTest {

    @DisplayName("10000원 어치를 사고 5등이 1장 당첨됐을때 수익률은 0.5이다")
    @Test
    void calculateEarningRate_10000_5th_1() {
        PrizeInformation prizeInformation = new PrizeInformation(Prize.FIFTH, 1);
        PrizeInformations prizeInformations = new PrizeInformations(List.of(prizeInformation));

        assertThat(prizeInformations.calculateEarningRate(Money.from("10000"))).isEqualTo(0.5);
    }

    @DisplayName("100000원 어치를 사고 4등 1장, 5등 5장 당첨됐을때 수익률은 0.75이다")
    @Test
    void calculateEarningRate_100000_4th_1_5th_5() {
        PrizeInformation fourthPrizeInformation = new PrizeInformation(Prize.FOURTH, 1);
        PrizeInformation fifthPrizeInformation = new PrizeInformation(Prize.FIFTH, 5);
        PrizeInformations prizeInformations =
                new PrizeInformations(List.of(fourthPrizeInformation, fifthPrizeInformation));

        assertThat(prizeInformations.calculateEarningRate(Money.from("100000"))).isEqualTo(0.75);
    }
}
