package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoTest {
    @Test
    void winner_number_match_all() {
        Lotto lotto = new Lotto(Arrays.asList(
                Number.of(1),
                Number.of(2),
                Number.of(3),
                Number.of(4),
                Number.of(5),
                Number.of(6)
        ));
        assertThat(new WinningLotto(new WinningNumbers("1, 2, 3, 4, 5, 6"), new Number(7)).getRank(lotto)).isEqualTo(Rank.FIRST);
    }

    @Test
    void winner_number_match_five_include_bonus() {
        Lotto lotto = new Lotto(Arrays.asList(
                Number.of(1),
                Number.of(2),
                Number.of(3),
                Number.of(4),
                Number.of(5),
                Number.of(7)
        ));
        assertThat(new WinningLotto(new WinningNumbers("1, 2, 3, 4, 5, 6"), new Number(7)).getRank(lotto)).isEqualTo(Rank.SECOND);
    }

    @Test
    void winner_number_match_five_except_bonus() {
        Lotto lotto = new Lotto(Arrays.asList(
                Number.of(1),
                Number.of(2),
                Number.of(3),
                Number.of(4),
                Number.of(5),
                Number.of(7)
        ));
        assertThat(new WinningLotto(new WinningNumbers("1, 2, 3, 4, 5, 6"), new Number(8)).getRank(lotto)).isEqualTo(Rank.THIRD);
    }


    @Test
    void winner_number_match_four() {
        Lotto lotto = new Lotto(Arrays.asList(
                Number.of(1),
                Number.of(2),
                Number.of(3),
                Number.of(4),
                Number.of(7),
                Number.of(8)
        ));
        assertThat(new WinningLotto(new WinningNumbers("1, 2, 3, 4, 5, 6"), new Number(7)).getRank(lotto)).isEqualTo(Rank.FOURTH);
    }

    @Test
    void winner_number_match_three() {
        Lotto lotto = new Lotto(Arrays.asList(
                Number.of(1),
                Number.of(2),
                Number.of(3),
                Number.of(7),
                Number.of(8),
                Number.of(9)
        ));
        assertThat(new WinningLotto(new WinningNumbers("1, 2, 3, 4, 5, 6"), new Number(7)).getRank(lotto)).isEqualTo(Rank.FIFTH);
    }

    @Test
    void winner_number_match_miss() {
        Lotto lotto = new Lotto(Arrays.asList(
                Number.of(1),
                Number.of(2),
                Number.of(10),
                Number.of(7),
                Number.of(8),
                Number.of(9)
        ));
        assertThat(new WinningLotto(new WinningNumbers("1, 2, 3, 4, 5, 6"), new Number(7)).getRank(lotto)).isEqualTo(Rank.MISS);
    }
}