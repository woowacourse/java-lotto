package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RankTest {

    @ParameterizedTest
    @CsvSource({
            "0, true",
            "1, false",
            "2, false"
    })
    @DisplayName("등수에 해당하지 않으면 6등 반환 테스트")
    void checkNoWinTest(int count, boolean hasBonusBall) {
        Rank rank = Rank.valueOf(count, hasBonusBall);
        assertThat(rank).isEqualTo(Rank.SIXTH);
    }

    @ParameterizedTest
    @CsvSource({
            "7, true",
            "8, false",
            "9, false"
    })
    @DisplayName("일치 개수가 6개 초과로 생성 시 예외 발생")
    void checkSixOverMatchTest(int count, boolean hasBonusBall) {
        assertThatThrownBy(() -> Rank.valueOf(count, hasBonusBall))
                .isInstanceOf(IllegalArgumentException.class);
    }
}