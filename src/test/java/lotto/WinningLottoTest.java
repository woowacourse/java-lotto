package lotto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {
    @Test
    void 보너스볼이_숫자인지_확인() {
        String winningLotto = "1, 2, 3, 4, 5, 6";
        String falseBonusBall = "a";
        assertThatThrownBy(() -> {
            new WinningLotto(winningLotto, falseBonusBall);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 숫자만 입력할 수 있습니다");
    }

    @Test
    void 보너스볼이_범위에_있는지_확인() {
        String winningLotto = "1, 2, 3, 4, 5, 6";
        String falseBonusBall = "46";
        assertThatThrownBy(() -> {
            new WinningLotto(winningLotto, falseBonusBall);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 보너스볼 숫자를 1 ~ 45 사이로 입력해주세요");
    }

    @Test
    void 보너스볼이_당첨번호와_중복인지_확인() {
        String winningLotto = "1, 2, 3, 4, 5, 6";
        String falseBonusBall = "1";
        assertThatThrownBy(() -> {
            new WinningLotto(winningLotto, falseBonusBall);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 보너스볼 숫자는 당첨번호와 중복될 수 없습니다");
    }

    @Test
    void 보너스볼_맞는지_확인() {
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6", "7");
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 7));
        Lotto lotto = new Lotto(numbers);
        boolean expected = winningLotto.hasBonusBall(lotto);
        assertThat(expected).isEqualTo(true);
    }

    @Test
    void 몇등에_당첨인지_확인() {
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6", "7");
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 7));
        Lotto lotto = new Lotto(numbers);
        Rank rank = winningLotto.findRank(lotto);
        assertThat(rank).isEqualTo(Rank.SECOND);
    }
}
