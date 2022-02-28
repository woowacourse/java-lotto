package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("로또 생성 시 로또 번호가 6개인지 체크")
    void lotto_numbers_count() {
        assertThatThrownBy(() -> {
            new Lotto(List.of(1, 2, 3));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 생성 시 로또 숫자가 중복인지 체크")
    void lotto_number_duplicated() {
        assertThatThrownBy(() -> {
            new Lotto(List.of(1, 2, 2, 3, 4, 5));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또가 이미 포함하고 있는 숫자인지 체크")
    void lotto_contains_number() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber lottoNumber = new LottoNumber(3);

        assertTrue(lotto.contains(lottoNumber));
    }

    @Test
    @DisplayName("로또의 숫자가 모두 일치할 때 1등 반환")
    void calc_same_count() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto winLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusBall = new LottoNumber(7);

        assertEquals(Rank.FIRST, lotto.getRank(new WinningLotto(winLotto, bonusBall)));
    }

    @Test
    @DisplayName("로또의 숫자가 5개 일치하고 보너스볼이 일치할 때 2등 반환")
    void calc_same_count_different() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto winLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusBall = new LottoNumber(7);

        assertEquals(Rank.SECOND, lotto.getRank(new WinningLotto(winLotto, bonusBall)));
    }
}
