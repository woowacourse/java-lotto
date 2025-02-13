package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RankTest {

    @Test
    void fromResult() {
        Assertions.assertThat(Rank.fromResult(6, true)).isEqualTo(Rank.FIRST);
        Assertions.assertThat(Rank.fromResult(6, false)).isEqualTo(Rank.FIRST);

        Assertions.assertThat(Rank.fromResult(5, true)).isEqualTo(Rank.SECOND);
        Assertions.assertThat(Rank.fromResult(5, false)).isEqualTo(Rank.THIRD);

        Assertions.assertThat(Rank.fromResult(4, true)).isEqualTo(Rank.FOURTH);
        Assertions.assertThat(Rank.fromResult(4, false)).isEqualTo(Rank.FOURTH);

        Assertions.assertThat(Rank.fromResult(3, true)).isEqualTo(Rank.FIFTH);
        Assertions.assertThat(Rank.fromResult(3, false)).isEqualTo(Rank.FIFTH);

        Assertions.assertThat(Rank.fromResult(2, true)).isEqualTo(Rank.NONE);
        Assertions.assertThat(Rank.fromResult(2, false)).isEqualTo(Rank.NONE);



    }
}