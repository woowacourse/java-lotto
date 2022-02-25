package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    private Lotto lotto;

    @Test
    void 로또_생성_테스트_정상() {
        lotto = new Lotto(() -> Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(lotto).isInstanceOf(Lotto.class);
    }


    @Test
    void 로또_생성_테스트_길이() {
        assertThatThrownBy(() ->
                lotto = new Lotto(() -> Arrays.asList(1, 2, 3, 4)))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 로또_생성_테스트_범위() {
        assertThatThrownBy(() ->
                lotto = new Lotto(() -> Arrays.asList(1, 2, 3, 4, -1, 0)))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 로또_생성_테스트_중복() {
        assertThatThrownBy(() ->
                lotto = new Lotto(() -> Arrays.asList(1, 2, 3, 4, 4)))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }

    @BeforeEach
    void init() {
        lotto = new Lotto(() -> Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    void 중복_테스트() {
        List<Integer> actual = lotto.getNumbers();
        assertThat(actual.stream().distinct().count()).isEqualTo(actual.size());
    }

    @Test
    void 로또_1등_당첨_테스트() {
        Rank rank = lotto.calculateRank(Arrays.asList(1, 2, 3, 4, 5, 6), new BonusNumber(7));
        assertThat(rank).isEqualTo((Rank.FIRST));
    }

    @Test
    void 로또_2등_당첨_테스트() {
        Rank rank = lotto.calculateRank(Arrays.asList(1, 2, 3, 4, 5, 7), new BonusNumber(6));
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    void 로또_3등_당첨_테스트() {
        Rank rank = lotto.calculateRank(Arrays.asList(1, 2, 3, 4, 5, 7), new BonusNumber(8));
        assertThat(rank).isEqualTo((Rank.THIRD));
    }

    @Test
    void 로또_4등_당첨_테스트() {
        Rank rank = lotto.calculateRank(Arrays.asList(1, 2, 3, 4, 7, 8), new BonusNumber(9));
        assertThat(rank).isEqualTo((Rank.FOURTH));
    }

    @Test
    void 로또_5등_당첨_테스트() {
        Rank rank = lotto.calculateRank(Arrays.asList(1, 2, 3, 7, 8, 9), new BonusNumber(10));
        assertThat(rank).isEqualTo((Rank.FIFTH));
    }

    @DisplayName("0개 일치")
    @Test
    void 로또_낙첨_테스트1() {
        Rank rank = lotto.calculateRank(Arrays.asList(7, 8, 9, 10, 11, 12), new BonusNumber(13));
        assertThat(rank).isEqualTo((Rank.LOSER));
    }

    @DisplayName("1개 일치")
    @Test
    void 로또_낙첨_테스트2() {
        Rank rank = lotto.calculateRank(Arrays.asList(1, 8, 9, 10, 11, 12), new BonusNumber(13));
        assertThat(rank).isEqualTo((Rank.LOSER));
    }

    @DisplayName("2개 일치")
    @Test
    void 로또_낙첨_테스트3() {
        Rank rank = lotto.calculateRank(Arrays.asList(1, 2, 9, 10, 11, 12), new BonusNumber(13));
        assertThat(rank).isEqualTo((Rank.LOSER));
    }
}
