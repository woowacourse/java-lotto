package lotto.model.prize;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lotto.model.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrizeInformationsTest {
    private List<MatchResult> matchResults;

    @BeforeEach
    void initializeMatchResults() {
        matchResults = new ArrayList<>();
    }

    @DisplayName("10000원 어치를 사고 5등이 1장 당첨됐을때 수익률은 0.5이다")
    @Test
    void calculateEarningRate_10000_5th_1() {
        addResult(new MatchResult(3, false), 1);

        PrizeInformation prizeInformation = PrizeInformation.of(matchResults, Prize.FIFTH);
        PrizeInformations prizeInformations = new PrizeInformations(List.of(prizeInformation));

        assertThat(prizeInformations.calculateEarningRate(Money.from("10000"))).isEqualTo(0.5);
    }

    @DisplayName("100000원 어치를 사고 4등 1장, 5등 5장 당첨됐을때 수익률은 0.75이다")
    @Test
    void calculateEarningRate_100000_4th_1_5th_5() {
        addResult(new MatchResult(4, false), 1);
        addResult(new MatchResult(3, false), 5);

        PrizeInformation fourthPrizeInformation = PrizeInformation.of(matchResults, Prize.FOURTH);
        PrizeInformation fifthPrizeInformation = PrizeInformation.of(matchResults, Prize.FIFTH);

        PrizeInformations prizeInformations =
                new PrizeInformations(List.of(fourthPrizeInformation, fifthPrizeInformation));

        assertThat(prizeInformations.calculateEarningRate(Money.from("100000"))).isEqualTo(0.75);
    }

    private void addResult(MatchResult matchResult, int count) {
        for (int i = 0; i < count; i++) {
            matchResults.add(matchResult);
        }
    }
}
