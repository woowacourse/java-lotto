package lotto.domain;

import static lotto.common.constant.Constant.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.dto.MatchCountDto;

class LottoTest {

    @Test
    @DisplayName("로또 번호의 갯수가 기준(6)과 다르다면 예외를 발생시킨다.")
    void testLottoNumber_sizeException() {
        assertThatThrownBy(() -> new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5))))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호의 범위가 기준에서 벗어난다면 예외를 발생시킨다.")
    void testLottoNumber_rangeException() {
        assertThatThrownBy(() -> new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, LOTTO_MAXIMUM + 1))))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Lotto(new ArrayList<>(List.of(LOTTO_MINIMUM - 1, 2, 3, 4, 5, 6))))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("MatchCount()에서 로또 당첨 개수와 보너스 여부를 반환한다.")
    void test_MatchCount() {
        Lotto lotto = new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)));
        Lotto matchLotto = new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, 8)));
        int bonus = 6;
        MatchCountDto dto = lotto.matchCount(matchLotto, bonus);

        assertThat(dto.matchCount()).isEqualTo(5);
        assertThat(dto.bonus()).isTrue();
    }

    @Test
    @DisplayName("로또를 정렬해서 반환한다.")
    void test_CheckSorted() {
        ArrayList<Integer> list = new ArrayList<>(List.of(6, 5, 4, 3, 2, 1));
        Lotto lotto = new Lotto(list);

        assertThat(lotto.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }

}
