package domain;

import static domain.rank.Rank.select;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.rank.Rank;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;

class RankTest {

    @DisplayName("유효한 값이면 랭킹 선택 성공")
    @Test
    void select_validInput_success() {
        assertAll(
                () -> assertThat(select(5, true)).isEqualTo(Rank.SECOND),
                () -> assertThat(select(5, false)).isEqualTo(Rank.THIRD),
                () -> assertThat(select(4, true)).isEqualTo(Rank.FOURTH)
        );
    }
}