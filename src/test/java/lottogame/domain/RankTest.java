package lottogame.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RankTest {
    @Test
    void 숫자에_맞는_랭크를_리턴하는지_테스트() {
        assertThat(Rank.valueOf(5, true)).isEqualTo(Rank.SECOND);
    }

    @Test
    void 범위를_벗어나는_매치개수를_입력했을때_예외처리_테스트() {
        assertThrows(IllegalArgumentException.class,()->Rank.valueOf(-1, false));
    }
}
