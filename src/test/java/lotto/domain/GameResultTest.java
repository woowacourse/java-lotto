package lotto.domain;

import lotto.domain.result.GameResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
    @Test
    void 게임_결과_생성() {
        // given
        int correctNumber = 6;
        boolean isCorrectBonusNumber = true;
        // when
        GameResult gr = GameResult.calculateRank(correctNumber, isCorrectBonusNumber);
        // then
        assertThat(gr).isEqualTo(GameResult.FIRST_RANK);

        correctNumber = 5;
        isCorrectBonusNumber = true;

        gr = GameResult.calculateRank(correctNumber, isCorrectBonusNumber);
        assertThat(gr).isEqualTo(GameResult.SECOND_RANK);

        correctNumber = 5;
        isCorrectBonusNumber = false;

        gr = GameResult.calculateRank(correctNumber, isCorrectBonusNumber);
        assertThat(gr).isEqualTo(GameResult.THIRD_RANK);

        correctNumber = 4;
        isCorrectBonusNumber = false;

        gr = GameResult.calculateRank(correctNumber, isCorrectBonusNumber);
        assertThat(gr).isEqualTo(GameResult.FOURTH_RANK);

        correctNumber = 3;
        isCorrectBonusNumber = false;

        gr = GameResult.calculateRank(correctNumber, isCorrectBonusNumber);
        assertThat(gr).isEqualTo(GameResult.FIFTH_RANK);

        correctNumber = 2;
        isCorrectBonusNumber = true;

        gr = GameResult.calculateRank(correctNumber, isCorrectBonusNumber);
        assertThat(gr).isEqualTo(GameResult.NO_RANK);


    }
}
