package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    private Lotto lotto;

    @BeforeEach
    void init() {
        lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    void 중복_테스트() {
        List<Integer> actual = lotto.getNumbers();
        assertThat(actual.stream().distinct().count()).isEqualTo(actual.size());
    }

    @Test
    void 로또_1등_당첨_테스트() {
        lotto.calculateRank(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
        assertThat(lotto.getRank()).isEqualTo((Rank.FIRST));
    }

    @Test
    void 로또_2등_당첨_테스트() {
        lotto.calculateRank(Arrays.asList(1, 2, 3, 4, 5, 7), 6);
        assertThat(lotto.getRank()).isEqualTo(Rank.SECOND);
    }

    @Test
    void 로또_3등_당첨_테스트() {
        lotto.calculateRank(Arrays.asList(1, 2, 3, 4, 5, 7), 8);
        assertThat(lotto.getRank()).isEqualTo((Rank.THIRD));
    }

    @Test
    void 로또_4등_당첨_테스트() {
        lotto.calculateRank(Arrays.asList(1, 2, 3, 4, 7, 8), 9);
        assertThat(lotto.getRank()).isEqualTo((Rank.FOURTH));
    }

    @Test
    void 로또_5등_당첨_테스트() {
        lotto.calculateRank(Arrays.asList(1, 2, 3, 7, 8, 9), 10);
        assertThat(lotto.getRank()).isEqualTo((Rank.FIFTH));
    }

    @DisplayName("0개 일치")
    @Test
    void 로또_낙첨_테스트1() {
        lotto.calculateRank(Arrays.asList(7, 8, 9, 10, 11, 12), 13);
        assertThat(lotto.getRank()).isEqualTo((Rank.LOSER));
    }

    @DisplayName("1개 일치")
    @Test
    void 로또_낙첨_테스트2() {
        lotto.calculateRank(Arrays.asList(1, 8, 9, 10, 11, 12), 13);
        assertThat(lotto.getRank()).isEqualTo((Rank.LOSER));
    }

    @DisplayName("2개 일치")
    @Test
    void 로또_낙첨_테스트3() {
        lotto.calculateRank(Arrays.asList(1, 2, 9, 10, 11, 12), 13);
        assertThat(lotto.getRank()).isEqualTo((Rank.LOSER));
    }
}
