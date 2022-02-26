package lotto.model.prize;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrizeInformationTest {
    private List<MatchResult> matchResults;

    @BeforeEach
    void initializeMatchResults() {
        matchResults = new ArrayList<>();
    }

    @DisplayName("5등이 3장 당첨됐을때 당첨금은 15000원이다")
    @Test
    void pickAmount_5th_3() {
        addResult(new MatchResult(3, false), 3);
        PrizeInformation prizeInformation = PrizeInformation.of(matchResults, Prize.FIFTH);
        assertThat(prizeInformation.pickAmount()).isEqualTo(15000);
    }

    @DisplayName("5등이 2장 당첨됐을때 당첨금은 10000원이다")
    @Test
    void pickAmount_5th_2() {
        addResult(new MatchResult(3, false), 2);
        PrizeInformation prizeInformation = PrizeInformation.of(matchResults, Prize.FIFTH);
        assertThat(prizeInformation.pickAmount()).isEqualTo(10000);
    }

    @DisplayName("4등이 3장 당첨됐을때 당첨금은 150000원이다")
    @Test
    void pickAmount_4th_3() {
        addResult(new MatchResult(4, false), 3);
        PrizeInformation prizeInformation = PrizeInformation.of(matchResults, Prize.FOURTH);
        assertThat(prizeInformation.pickAmount()).isEqualTo(150000);
    }


    private void addResult(MatchResult matchResult, int count) {
        for (int i = 0; i < count; i++) {
            matchResults.add(matchResult);
        }
    }
}
