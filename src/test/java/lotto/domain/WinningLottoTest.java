package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoTest {
    @Test
    void winner_number_match_all() {
        Lotto lotto = new Lotto(Arrays.asList(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6)
        ));
        assertThat(new WinningLotto("1, 2, 3, 4, 5, 6").getRank(lotto)).isEqualTo(Rank.FIRST);
    }

    @Test
    void winner_number_match_five() {
        Lotto lotto = new Lotto(Arrays.asList(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(7)
        ));
        assertThat(new WinningLotto("1, 2, 3, 4, 5, 6").getRank(lotto)).isEqualTo(Rank.THIRD);
    }

    @Test
    void winner_number_match_four() {
        Lotto lotto = new Lotto(Arrays.asList(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(7),
                LottoNumber.of(8)
        ));
        assertThat(new WinningLotto("1, 2, 3, 4, 5, 6").getRank(lotto)).isEqualTo(Rank.FOURTH);
    }

    @Test
    void winner_number_match_three() {
        Lotto lotto = new Lotto(Arrays.asList(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(7),
                LottoNumber.of(8),
                LottoNumber.of(9)
        ));
        assertThat(new WinningLotto("1, 2, 3, 4, 5, 6").getRank(lotto)).isEqualTo(Rank.FIFTH);
    }

    @Test
    void winner_number_match_miss() {
        Lotto lotto = new Lotto(Arrays.asList(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(10),
                LottoNumber.of(7),
                LottoNumber.of(8),
                LottoNumber.of(9)
        ));
        assertThat(new WinningLotto("1, 2, 3, 4, 5, 6").getRank(lotto)).isEqualTo(Rank.MISS);
    }
}