package lotto.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {

    @Test
    @DisplayName("맞은 볼 개수와 보너스 볼 포함 여부에 맞는 등수를 반환한다.")
    void findTest() {
        Rank actual1 = Rank.find(5, true);
        Rank actual2 = Rank.find(5, false);

        assertThat(actual1).isEqualTo(Rank.SECOND);
        assertThat(actual2).isEqualTo(Rank.THIRD);
    }
}
