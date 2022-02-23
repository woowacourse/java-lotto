package model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoRankTest {

    @Test
    @DisplayName("음수 일치 갯수로 생성 시 예외 발생")
    void createByNegativeMatchCount() {
        assertThatThrownBy(() -> LottoRank.of(-1, false))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("일치 갯수는 0이상 6이하이여야 합니다.");
    }

    @Test
    @DisplayName("일치 갯수가 6 초과되는 값으로 생성 시 예외 발생")
    void createByOverSixMatchCount() {
        assertThatThrownBy(() -> LottoRank.of(7, false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("일치 갯수는 0이상 6이하이여야 합니다.");
    }
}
