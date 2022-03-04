package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.constant.ErrorMessage.ERROR_RANK_NOT_EXIST;
import static org.assertj.core.api.Assertions.*;

class RankTest {

    @Test
    @DisplayName("맞는 개수에 따라 랭크를 반환한다")
    void getRank() {
        assertThat(Rank.of(6, false)).isEqualTo(Rank.FIRST);
        assertThat(Rank.of(5, true)).isEqualTo(Rank.SECOND);
        assertThat(Rank.of(5, false)).isEqualTo(Rank.THIRD);
        assertThat(Rank.of(4, false)).isEqualTo(Rank.FOURTH);
        assertThat(Rank.of(3, false)).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("존재하지 않는 순위일 경우 예외를 발생시킨다.")
    void throwExceptionNotExist() {
        assertThatThrownBy(() -> Rank.of(1, false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_RANK_NOT_EXIST.message());
    }
}
