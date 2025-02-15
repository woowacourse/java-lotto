package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RankTest {
    @DisplayName("숫자가 6개 일치하면 1등이다.")
    @Test
    void 당첨_결과가_1등인_경우() {
        Rank expectedRank = Rank.FIRST;
        Rank rank = Rank.findRank(6, false);

        assertEquals(expectedRank, rank);
    }

    @DisplayName("숫자가 5개 일치하고, 보너스 번호가 일치하면 2등이다.")
    @Test
    void 당첨_결과가_2등인_경우() {
        Rank expectedRank = Rank.SECOND;
        Rank rank = Rank.findRank(5, true);

        assertEquals(expectedRank, rank);
    }

    @DisplayName("숫자가 5개 일치하고, 보너스 번호가 일치하지 않으면 3등이다.")
    @Test
    void 당첨_결과가_3등인_경우() {
        Rank expectedRank = Rank.THIRD;
        Rank rank = Rank.findRank(5, false);

        assertEquals(expectedRank, rank);
    }

    @DisplayName("숫자가 4개 일치하면 4등이다.")
    @Test
    void 당첨_결과가_4등인_경우() {
        Rank expectedRank = Rank.FOURTH;
        Rank rank = Rank.findRank(4, false);

        assertEquals(expectedRank, rank);
    }

    @DisplayName("숫자가 3개 일치하면 5등이다.")
    @Test
    void 당첨_결과가_5등인_경우() {
        Rank expectedRank = Rank.FIFTH;
        Rank rank = Rank.findRank(3, false);

        assertEquals(expectedRank, rank);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 1, 0})
    @DisplayName("숫자가 3개 미만으로 일치하면 낙첨이다.")
    void 당첨_결과가_낙첨인_경우(int matchCount) {
        Rank expectedRank = Rank.NONE;
        Rank rank = Rank.findRank(matchCount, false);

        assertEquals(expectedRank, rank);
    }
}
