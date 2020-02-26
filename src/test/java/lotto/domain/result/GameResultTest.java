package lotto.domain.result;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 우승자_생성() {
        // given
        int correctNumber = 6;
        boolean isCorrectBonusNumber = false;
        // when
        GameResult gr = GameResult.calculateRank(correctNumber, isCorrectBonusNumber);
        // then
        assertThat(gr).isEqualTo(GameResult.FIRST_RANK);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 이등_생성() {
        // given
        int correctNumber = 5;
        boolean isCorrectBonusNumber = true;
        // when
        GameResult gr = GameResult.calculateRank(correctNumber, isCorrectBonusNumber);
        // then
        assertThat(gr).isEqualTo(GameResult.SECOND_RANK);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 삼등_생성() {
        // given
        int correctNumber = 5;
        boolean isCorrectBonusNumber = false;
        // when
        GameResult gr = GameResult.calculateRank(correctNumber, isCorrectBonusNumber);
        // then
        assertThat(gr).isEqualTo(GameResult.THIRD_RANK);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 사등_생성() {
        // given
        int correctNumber = 4;
        boolean isCorrectBonusNumber = false;
        // when
        GameResult gr = GameResult.calculateRank(correctNumber, isCorrectBonusNumber);
        // then
        assertThat(gr).isEqualTo(GameResult.FOURTH_RANK);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 오등_생성() {
        // given
        int correctNumber = 3;
        boolean isCorrectBonusNumber = false;
        // when
        GameResult gr = GameResult.calculateRank(correctNumber, isCorrectBonusNumber);
        // then
        assertThat(gr).isEqualTo(GameResult.FIFTH_RANK);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 꼴등_생성() {
        // given
        int correctNumber = 2;
        boolean isCorrectBonusNumber = false;
        // when
        GameResult gr = GameResult.calculateRank(correctNumber, isCorrectBonusNumber);
        // then
        assertThat(gr).isEqualTo(GameResult.NO_RANK);
    }
}
