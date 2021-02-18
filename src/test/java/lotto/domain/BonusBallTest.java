package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusBallTest {
    private Lotto winLotto;

    @BeforeEach
    void setUp() {
        ArrayList<Integer> winningLotto = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        winLotto = new Lotto(winningLotto);
    }

    @Test
    void 보너스볼이_숫자인지_확인() {
        String falseBonusBall = "a";
        assertThatThrownBy(() -> {
            new BonusBall(winLotto, falseBonusBall);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 숫자만 입력할 수 있습니다");
    }

    @Test
    void 보너스볼이_범위에_있는지_확인() {
        String falseBonusBall = "46";
        assertThatThrownBy(() -> {
            new BonusBall(winLotto, falseBonusBall);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 보너스볼 숫자를 1 ~ 45 사이로 입력해주세요");
    }

    @Test
    void 보너스볼이_당첨번호와_중복인지_확인() {
        String falseBonusBall = "1";
        assertThatThrownBy(() -> {
            new BonusBall(winLotto, falseBonusBall);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 보너스볼 숫자는 당첨번호와 중복될 수 없습니다");
    }

    @Test
    void 보너스볼_맞는지_확인() {
        BonusBall bonusBall = new BonusBall(winLotto, "7");
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 7));
        Lotto lotto = new Lotto(numbers);
        boolean expected = bonusBall.hasBonusBall(lotto);
        assertThat(expected).isEqualTo(true);
    }
}
