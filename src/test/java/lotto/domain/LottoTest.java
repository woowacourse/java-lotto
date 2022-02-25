package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class LottoTest {

    @Test
    @DisplayName("로또 생성 시 Ball 객체가 6개인지 체크")
    void ball_count() {
        List<String> numbers = new ArrayList<>(Arrays.asList("1", "2", "3"));

        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(numbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 생성 시 Ball 숫자 중복")
    void ball_duplicated() {
        List<String> numbers = new ArrayList<>(Arrays.asList("1", "2", "2", "3", "4", "5"));

        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(numbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또의 숫자가 모두 일치할때 1등 반환")
    void calc_same_count() {
        Lotto lotto = new Lotto(Arrays.asList("1", "2", "3", "4", "5", "6"));
        Lotto winLotto = new Lotto(Arrays.asList("1", "2", "3", "4", "5", "6"));
        Ball bonusBall = new Ball("7");

        assertEquals(Rank.FIRST, lotto.getRank(new WinningLotto(winLotto, bonusBall)));
    }

    @Test
    @DisplayName("로또의 숫자가 5개 일치하고 보너스볼이 일치할 때 2등 반환")
    void calc_same_count_different() {
        Lotto lotto = new Lotto(Arrays.asList("1", "2", "3", "4", "5", "7"));
        Lotto winLotto = new Lotto(Arrays.asList("1", "2", "3", "4", "5", "6"));
        Ball bonusBall = new Ball("7");

        assertEquals(Rank.SECOND, lotto.getRank(new WinningLotto(winLotto, bonusBall)));
    }

}
