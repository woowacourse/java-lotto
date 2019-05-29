package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Number;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoTest {
    private Lotto winningNumbers;
    @BeforeEach
    void setUp() {
        winningNumbers = new Lotto(Arrays.asList(
                Number.of(1),
                Number.of(2),
                Number.of(3),
                Number.of(4),
                Number.of(5),
                Number.of(6)
        ));
    }

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
        assertThat(new WinningLotto(winningNumbers, new Number(7)).getRank(lotto)).isEqualTo(Rank.FIRST);
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
        assertThat(new WinningLotto(winningNumbers, new Number(7)).getRank(lotto)).isEqualTo(Rank.SECOND);
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
        assertThat(new WinningLotto(winningNumbers, new Number(8)).getRank(lotto)).isEqualTo(Rank.THIRD);
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
        assertThat(new WinningLotto(winningNumbers, new Number(7)).getRank(lotto)).isEqualTo(Rank.FOURTH);
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
        assertThat(new WinningLotto(winningNumbers, new Number(7)).getRank(lotto)).isEqualTo(Rank.FIFTH);
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
        assertThat(new WinningLotto(winningNumbers, new Number(7)).getRank(lotto)).isEqualTo(Rank.MISS);
    }
}