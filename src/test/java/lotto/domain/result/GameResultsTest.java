package lotto.domain.result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultsTest {
    private final List<GameResult> results = new ArrayList<>();

    @BeforeEach
    @SuppressWarnings("NonAsciiCharacters")
    void 결과_생성() {
        results.add(GameResult.FIRST_RANK);
        results.add(GameResult.FIRST_RANK);
        results.add(GameResult.SECOND_RANK);
        results.add(GameResult.THIRD_RANK);
        results.add(GameResult.FOURTH_RANK);
        results.add(GameResult.FIFTH_RANK);
        results.add(GameResult.FIFTH_RANK);
        results.add(GameResult.FIFTH_RANK);
        results.add(GameResult.FIFTH_RANK);
        results.add(GameResult.NO_RANK);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 게임결과들생성() {
        // given
        List<GameResult> results = new ArrayList<>();
        // when
        GameResults gameResults = new GameResults(results);
        // then
        assertThat(gameResults).isInstanceOf(GameResults.class);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 게임_결과_케이스_수_체크() {
        GameResults gameResults = new GameResults(results);
        assertThat(gameResults.calculateCaseNumberSize(GameResult.FIRST_RANK)).isEqualTo(2);
        assertThat(gameResults.calculateCaseNumberSize(GameResult.SECOND_RANK)).isEqualTo(1);
        assertThat(gameResults.calculateCaseNumberSize(GameResult.THIRD_RANK)).isEqualTo(1);
        assertThat(gameResults.calculateCaseNumberSize(GameResult.FOURTH_RANK)).isEqualTo(1);
        assertThat(gameResults.calculateCaseNumberSize(GameResult.FIFTH_RANK)).isEqualTo(4);
        assertThat(gameResults.calculateCaseNumberSize(GameResult.NO_RANK)).isEqualTo(1);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 수익률_계산() {
        // given
        Money money = new Money(7000);
        GameResult round1 = GameResult.FIRST_RANK; // 2000000000
        GameResult round2 = GameResult.FIFTH_RANK; // 5000
        GameResult round3 = GameResult.SECOND_RANK; // 30000000
        GameResult round4 = GameResult.FIRST_RANK;// 2000000000
        GameResult round5 = GameResult.NO_RANK; // 0
        GameResult round6 = GameResult.NO_RANK; // 0
        GameResult round7 = GameResult.FIRST_RANK;// 6030005000

        List<GameResult> rounds = new ArrayList<>();
        rounds.add(round1);
        rounds.add(round2);
        rounds.add(round3);
        rounds.add(round4);
        rounds.add(round5);
        rounds.add(round6);
        rounds.add(round7);
        GameResults gameResults = new GameResults(rounds);
        // when
        double result = gameResults.calculateYield(money);
        double sumOfBenefit = 6030005000.0;
        // then
        assertThat(result).isEqualTo((sumOfBenefit / (7 * Money.TICKET_PRICE)) * GameResults.MULTIPLE_PERCENTAGE);
    }
}
