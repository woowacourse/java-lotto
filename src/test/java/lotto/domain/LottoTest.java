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
    @DisplayName("로또 생성 시 Ball 객체 6개 생성")
    void ball_count() {
        List<String> numbers = new ArrayList<>(Arrays.asList("1", "2", "3"));

        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(numbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 생성 시 Ball 숫자 중복")
    void ball_duplicated() {
        List<String> numbers = new ArrayList<>(Arrays.asList("1", "2", "2", "4", "5", "6"));

        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(numbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스볼 중복")
    void bonus_ball_duplicated() {
        List<String> numbers = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6"));
        Lotto lotto = new Lotto(numbers);
        Ball bonusBall = new Ball("1");

        assertTrue(lotto.contains(bonusBall));
    }

    @Test
    @DisplayName("로또의 숫자가 모두 일치할 때")
    void calc_same_count() {
        Lotto lotto = new Lotto(Arrays.asList("1", "2", "3", "4", "5", "6"));
        Lotto winLotto = new Lotto(Arrays.asList("1", "2", "3", "4", "5", "6"));

        int matchingCount = lotto.getMatchingCount(winLotto);

        assertEquals(matchingCount, 6);
    }

    @Test
    @DisplayName("로또의 숫자가 일치하지 않을 때")
    void calc_same_count_different() {
        Lotto lotto = new Lotto(Arrays.asList("1", "2", "3", "4", "5", "7"));
        Lotto winLotto = new Lotto(Arrays.asList("1", "2", "3", "4", "5", "6"));

        int matchingCount = lotto.getMatchingCount(winLotto);

        assertNotEquals(matchingCount, 6);
    }

}
