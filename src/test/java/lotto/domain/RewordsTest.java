package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RewordsTest {

    private static Rewords rewords;

    @BeforeEach
    void setUp() {
        rewords = new Rewords(Arrays.asList(Reword.FIRST, Reword.SECOND, Reword.SECOND));
    }

    @DisplayName("포함된 개수 테스트")
    @Test
    void rewordRankCount() {
        assertThat(rewords.getRankCount(Reword.FIRST)).isEqualTo(1);
        assertThat(rewords.getRankCount(Reword.SECOND)).isEqualTo(2);
        assertThat(rewords.getRankCount(Reword.THIRD)).isEqualTo(0);
    }

    @DisplayName("수익률 테스트")
    @Test
    void profit() {
        assertThat(rewords.profit(10_000_000)).isEqualTo(206);
    }
}
