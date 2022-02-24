package lotto.model.prize;

import java.util.List;
import java.util.stream.Collectors;
import lotto.model.Lotto;
import org.junit.jupiter.api.BeforeEach;

public class PrizeTest {
    private Lotto lotto;
    private List<String> lottoNumbers;

    @BeforeEach
    public void initializeLotto() {
        lotto = new Lotto();
        lottoNumbers = lotto.getNumbers().stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    /*
    @DisplayName("3개가 일치하면 5등을 반환한다")
    @Test
    void match_3_fifth() {
        assertThat(Prize.getPrize(3, false)).isEqualTo(Prize.FIFTH);
    }

    @DisplayName("4개가 일치하면 4등을 반환한다")
    @Test
    void match_4_fourth() {
        assertThat(Prize.getPrize(4, false)).isEqualTo(Prize.FOURTH);
    }

    @DisplayName("5개가 일치하면 3등을 반환한다")
    @Test
    void match_5_third() {
        assertThat(Prize.getPrize(5, false)).isEqualTo(Prize.THIRD);
    }

    @DisplayName("6개가 일치하면 1등을 반환한다")
    @Test
    void match_6_first() {
        assertThat(Prize.getPrize(6, false)).isEqualTo(Prize.FIRST);
    }

    @DisplayName("5개가 일치하고 보너스가 일치하면 2등을 반환한다")
    @Test
    void match_5_bonus_second() {
        assertThat(Prize.getPrize(5, true)).isEqualTo(Prize.SECOND);
    }
     */
}
