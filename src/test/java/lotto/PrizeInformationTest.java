package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrizeInformationTest {

    @DisplayName("5등이 3장 당첨됐을때 당첨금은 15000원이다")
    @Test
    void pickAmount_5th_3() {
        PrizeInformation prizeInformation = new PrizeInformation(Prize.FIFTH, 3);
        assertThat(prizeInformation.pickAmount()).isEqualTo(15000);
    }

    @DisplayName("5등이 2장 당첨됐을때 당첨금은 10000원이다")
    @Test
    void pickAmount_5th_2() {
        PrizeInformation prizeInformation = new PrizeInformation(Prize.FIFTH, 2);
        assertThat(prizeInformation.pickAmount()).isEqualTo(10000);
    }

    @DisplayName("4등이 3장 당첨됐을때 당첨금은 150000원이다")
    @Test
    void pickAmount_4th_3() {
        PrizeInformation prizeInformation = new PrizeInformation(Prize.FOURTH, 3);
        assertThat(prizeInformation.pickAmount()).isEqualTo(150000);
    }
}
