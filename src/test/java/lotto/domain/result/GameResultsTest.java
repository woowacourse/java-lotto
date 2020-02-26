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
    void 게임_결과들의_수익합_계산() {
        //given
        GameResults gameResults = new GameResults(results);
        // when
        double result = gameResults.calculateBenefit();
        double expected = 4031570000.0;
        // then
        assertThat(result).isEqualTo(expected);
    }
}
