package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import lotto.exceptions.BonusNumberException;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Number;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertThat(new WinningLotto(winningNumbers, Number.of(7)).resultOf(lotto)).isEqualTo(Rank.FIRST);
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
        assertThat(new WinningLotto(winningNumbers, Number.of(7)).resultOf(lotto)).isEqualTo(Rank.SECOND);
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
        assertThat(new WinningLotto(winningNumbers, Number.of(8)).resultOf(lotto)).isEqualTo(Rank.THIRD);
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
        assertThat(new WinningLotto(winningNumbers, Number.of(7)).resultOf(lotto)).isEqualTo(Rank.FOURTH);
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
        assertThat(new WinningLotto(winningNumbers, Number.of(7)).resultOf(lotto)).isEqualTo(Rank.FIFTH);
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
        assertThat(new WinningLotto(winningNumbers, Number.of(7)).resultOf(lotto)).isEqualTo(Rank.MISS);
    }

    @Test
    void bonus_number_cannot_be_duplicated_with_winning_numbers() {
        assertThrows(BonusNumberException.class, () -> {
            new WinningLotto(winningNumbers, Number.of(1));
        });
    }
}