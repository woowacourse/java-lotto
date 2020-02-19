package lotto.domain.result;

import lotto.view.OutputView;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultsTest {

    @Test
    void 게임결과들생성() {
        // given
        List<GameResult> results = new ArrayList<>();
        // when
        GameResults gameResults = new GameResults(results);
        // then
        assertThat(gameResults).isInstanceOf(GameResults.class);
    }

    @Test
    void 게임_결과_케이스_수_체크() {
        List<GameResult> results = new ArrayList<>();
        results.add(GameResult.FIRST_RANK);
        results.add(GameResult.SECOND_RANK);
        results.add(GameResult.SECOND_RANK);
        results.add(GameResult.FOURTH_RANK);
        results.add(GameResult.FOURTH_RANK);
        results.add(GameResult.NO_RANK);
        results.add(GameResult.NO_RANK);
        results.add(GameResult.NO_RANK);

        GameResults gameResults = new GameResults(results);

        assertThat(gameResults.calculateCaseNumberSize(GameResult.FIRST_RANK)).isEqualTo(1);
        assertThat(gameResults.calculateCaseNumberSize(GameResult.SECOND_RANK)).isEqualTo(2);
        assertThat(gameResults.calculateCaseNumberSize(GameResult.FOURTH_RANK)).isEqualTo(2);
        assertThat(gameResults.calculateCaseNumberSize(GameResult.NO_RANK)).isEqualTo(3);
    }

    @Test
    void 게임_결과물_출력() {
        // given
        List<GameResult> results = new ArrayList<>();
        results.add(GameResult.FIRST_RANK);
        results.add(GameResult.SECOND_RANK);
        results.add(GameResult.THIRD_RANK);
        results.add(GameResult.FOURTH_RANK);
        results.add(GameResult.FIRST_RANK);
        results.add(GameResult.FIFTH_RANK);
        results.add(GameResult.FIFTH_RANK);
        results.add(GameResult.FIFTH_RANK);
        results.add(GameResult.FIFTH_RANK);
        results.add(GameResult.NO_RANK);

        // when
        OutputView.printGameResults(new GameResults(results));
    }
}
