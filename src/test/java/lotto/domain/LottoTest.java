package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("로또 생성 시 로또 번호가 6개인지 체크")
    void count_lotto_number() {
        assertThatThrownBy(() -> {
            new Lotto(List.of(1, 2, 3));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 생성 시 로또 숫자가 중복인지 체크")
    void duplicated_lotto_number() {
        assertThatThrownBy(() -> {
            new Lotto(List.of(1, 2, 2, 3, 4, 5));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또를 비교하고 일치하는 숫자의 개수 확인")
    void get_lotto_match_count() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(4, 5, 6, 7, 8, 9));

        assertThat(lotto1.getMatchingCount(lotto2)).isEqualTo(3);
    }

    @Test
    @DisplayName("로또가 이미 포함하고 있는 숫자인지 체크")
    void contains_lotto_number() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber lottoNumber = new LottoNumber(3);

        assertThat(lotto.contains(lottoNumber)).isTrue();
    }
}
