package lotto.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {

    @Test
    void 중복_테스트() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> actual = lotto.getNumbers();
        assertThat(actual.stream()
                .distinct()
                .count()).isEqualTo(actual.size());
    }

    @Test
    void 로또_3등_당첨테스트() {
        Lotto lotto = new Lotto(Arrays.asList(1,2,3,4,6,8));
        lotto.calculateRank(Arrays.asList(1,2,3,4,5,6), 7);
        assertThat(lotto.getRank()).isEqualTo((Rank.THIRD));
    }

    @Test
    void 로또_2등_당첨테스트() {
        Lotto lotto = new Lotto(Arrays.asList(1,2,3,4,6,7));
        lotto.calculateRank(Arrays.asList(1,2,3,4,5,6), 7);
        assertThat(lotto.getRank()).isEqualTo(Rank.SECOND);
    }
}
