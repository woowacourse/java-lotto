package lotto.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


import lotto.exception.InvalidRankException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {

    @Test
    @DisplayName("음수 일치 갯수로 생성 시 예외 발생")
    void createByNegativeMatchCount() {
        assertThatThrownBy(() -> Rank.of(-1, false))
            .isInstanceOf(InvalidRankException.class);
    }

    @Test
    @DisplayName("일치 갯수가 6 초과되는 값으로 생성 시 예외 발생")
    void createByOverSixMatchCount() {
        assertThatThrownBy(() -> Rank.of(7, false))
            .isInstanceOf(InvalidRankException.class);
    }
}
