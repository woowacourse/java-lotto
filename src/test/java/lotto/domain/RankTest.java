package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class RankTest {

    @DisplayName("1등의 상금을 계산할 수 있다.")
    @Test
    void calculateFirstRankMoney() {
        final Rank first = Rank.FIRST;
        final long count = 3;

        assertThat(Rank.calculateMoney(first, count)).isEqualTo(6000000000L);
    }

    @DisplayName("2등의 상금을 계산할 수 있다.")
    @Test
    void calculateSecondRankMoney() {
        final Rank second = Rank.SECOND;
        final long count = 2;

        assertThat(Rank.calculateMoney(second, count)).isEqualTo(60000000L);
    }

    @DisplayName("3등의 상금을 계산할 수 있다.")
    @Test
    void calculateThirdRankMoney() {
        final Rank third = Rank.THIRD;
        final long count = 4;

        assertThat(Rank.calculateMoney(third, count)).isEqualTo(6000000L);
    }

    @DisplayName("4등의 상금을 계산할 수 있다.")
    @Test
    void calculateFourthRankMoney() {
        final Rank fourth = Rank.FOURTH;
        final long count = 2;

        assertThat(Rank.calculateMoney(fourth, count)).isEqualTo(100000);
    }

    @DisplayName("5등의 상금을 계산할 수 있다.")
    @Test
    void calculateFifthRankMoney() {
        final Rank fifth = Rank.FIFTH;
        final long count = 2;

        assertThat(Rank.calculateMoney(fifth, count)).isEqualTo(10000);
    }

    @DisplayName("맞은 갯수와 보너스 여부를 가지고 랭크를 반환할 수 있다.")
    @Nested
    class CalculateRank {

        @DisplayName("1등 반환")
        @Test
        void firstRank() {
            final Rank rank = Rank.calculateCurrentRank(6, false);

            assertThat(rank).isEqualTo(Rank.FIRST);
        }

        @DisplayName("2등 반환")
        @Test
        void secondRank() {
            final Rank rank = Rank.calculateCurrentRank(5, true);

            assertThat(rank).isEqualTo(Rank.SECOND);
        }

        @DisplayName("3등 반환")
        @Test
        void thirdRank() {
            final Rank rank = Rank.calculateCurrentRank(5, false);

            assertThat(rank).isEqualTo(Rank.THIRD);
        }

        @DisplayName("4등 반환")
        @Test
        void fourthRank() {
            final Rank rank = Rank.calculateCurrentRank(4, false);

            assertThat(rank).isEqualTo(Rank.FOURTH);
        }

        @DisplayName("5등 반환")
        @Test
        void fifthRank() {
            final Rank rank = Rank.calculateCurrentRank(3, false);

            assertThat(rank).isEqualTo(Rank.FIFTH);
        }

        @DisplayName("Nothing 반환")
        @Test
        void NothingRank() {
            final Rank rank = Rank.calculateCurrentRank(2, false);

            assertThat(rank).isEqualTo(Rank.NOT_THING);
        }

        @DisplayName("케이스에 존재하지 않음")
        @Test
        void exceptionRank() {
            assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> Rank.calculateCurrentRank(-1, false))
                    .withMessage("[ERROR] 해당하는 랭크가 없습니다.");
        }
    }

    @DisplayName("초기 생성 맵을 생성할 수 있다.")
    @Test
    void createRankMap() {
        final Map<Rank, Integer> expected = new HashMap<>();
        expected.put(Rank.FIRST, 0);
        expected.put(Rank.SECOND, 0);
        expected.put(Rank.THIRD, 0);
        expected.put(Rank.FOURTH, 0);
        expected.put(Rank.FIFTH, 0);
        expected.put(Rank.NOT_THING, 0);

        assertThat(Rank.createInitResultMap()).isEqualTo(expected);
    }
}
