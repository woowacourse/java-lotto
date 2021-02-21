package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.Prize.select;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PrizeTest {
    @DisplayName("유효한 값이면 랭킹 선택 성공")
    @Test
    void select_validInput_success() {
        assertAll(
                () -> assertThat(select(5, true)).isEqualTo(Prize.SECOND),
                () -> assertThat(select(5, false)).isEqualTo(Prize.THIRD),
                () -> assertThat(select(4, true)).isEqualTo(Prize.FOURTH)
        );
    }
}