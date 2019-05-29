package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoTest {
    @Test
    void winner_number_match_all() {
        Lotto lotto = new Lotto(Arrays.asList(
                LottoNumber.is(1),
                LottoNumber.is(2),
                LottoNumber.is(3),
                LottoNumber.is(4),
                LottoNumber.is(5),
                LottoNumber.is(6)
        ));
        assertThat(new WinningLotto("1, 2, 3, 4, 5, 6").getRank(lotto)).isEqualTo(Rank.FIRST);
    }

    @Test
    void winner_number_match_five() {
        Lotto lotto = new Lotto(Arrays.asList(
                LottoNumber.is(1),
                LottoNumber.is(2),
                LottoNumber.is(3),
                LottoNumber.is(4),
                LottoNumber.is(5),
                LottoNumber.is(7)
        ));
        assertThat(new WinningLotto("1, 2, 3, 4, 5, 6").getRank(lotto)).isEqualTo(Rank.THIRD);
    }

    @Test
    void winner_number_match_four() {
        Lotto lotto = new Lotto(Arrays.asList(
                LottoNumber.is(1),
                LottoNumber.is(2),
                LottoNumber.is(3),
                LottoNumber.is(4),
                LottoNumber.is(7),
                LottoNumber.is(8)
        ));
        assertThat(new WinningLotto("1, 2, 3, 4, 5, 6").getRank(lotto)).isEqualTo(Rank.FOURTH);
    }

    @Test
    void winner_number_match_three() {
        Lotto lotto = new Lotto(Arrays.asList(
                LottoNumber.is(1),
                LottoNumber.is(2),
                LottoNumber.is(3),
                LottoNumber.is(7),
                LottoNumber.is(8),
                LottoNumber.is(9)
        ));
        assertThat(new WinningLotto("1, 2, 3, 4, 5, 6").getRank(lotto)).isEqualTo(Rank.FIFTH);
    }

    @Test
    void winner_number_match_miss() {
        Lotto lotto = new Lotto(Arrays.asList(
                LottoNumber.is(1),
                LottoNumber.is(2),
                LottoNumber.is(10),
                LottoNumber.is(7),
                LottoNumber.is(8),
                LottoNumber.is(9)
        ));
        assertThat(new WinningLotto("1, 2, 3, 4, 5, 6").getRank(lotto)).isEqualTo(Rank.MISS);
    }
}