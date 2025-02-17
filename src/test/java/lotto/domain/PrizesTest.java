package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PrizesTest {

    @DisplayName("당첨 결과가 1등 2개, 3등 1개 일 때")
    @Test
    void 당첨_결과_개수_확인() {
        //given
        List<Rank> prizes = Arrays.asList(Rank.FIRST, Rank.FIRST, Rank.THIRD);
        Prizes prizeResults = new Prizes(prizes);

        //when
        Map<Rank, Integer> results = prizeResults.getResults();

        //then
        Assertions.assertThat(results).containsEntry(Rank.FIRST, 2);
        Assertions.assertThat(results).containsEntry(Rank.THIRD, 1);
    }

    @Test
    void 당첨_결과_없는_꼉우() {
        //given
        List<Rank> prizes = Arrays.asList(Rank.NONE, Rank.NONE);
        Prizes prizeResults = new Prizes(prizes);

        //when
        Map<Rank, Integer> results = prizeResults.getResults();

        //then
        Assertions.assertThat(results).containsEntry(Rank.FIRST, 0);
        Assertions.assertThat(results).containsEntry(Rank.SECOND, 0);
        Assertions.assertThat(results).containsEntry(Rank.THIRD, 0);
        Assertions.assertThat(results).containsEntry(Rank.FOURTH, 0);
        Assertions.assertThat(results).containsEntry(Rank.FIFTH, 0);
    }

    @Test
    void 당첨_결과_수익_계산_검증() {
        //given
        Money money = new Money("2000");
        List<Rank> prizes = Arrays.asList(Rank.FIRST, Rank.NONE);
        Prizes prizeResults = new Prizes(prizes);

        //when & then
        Assertions.assertThat(prizeResults.calculateProfit(money)).isEqualTo(1000000.0);
    }

}