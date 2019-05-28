package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class WinLottoTest {
    @Test
    void winner_number_check() {
        Lotto lotto = new Lotto(Arrays.asList(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6)
        ));
        assertThat(new WinLotto("1, 2, 3, 4, 5, 6").getRank(lotto)).isEqualTo(Rank.FIRST);
    }
}