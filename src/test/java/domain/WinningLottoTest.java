package domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @Test
    @DisplayName("당첨 로또 정상 생성")
    public void success_1() {
        List<Integer> lotto = List.of(1, 2, 3, 4, 5, 6);
        int bonus = 7;
        WinningLotto winningLotto = new WinningLotto(lotto, bonus);
        Assertions.assertThat(winningLotto).isInstanceOf(WinningLotto.class);
    }

    @Test
    @DisplayName("당첨 로또 중복시 예외")
    public void fail_1() {
        List<Integer> lotto = List.of(1, 2, 3, 5, 5, 6);
        int bonus = 7;

        Assertions.assertThatThrownBy(() -> new WinningLotto(lotto, bonus))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("당첨 번호는 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("보너스 번호 중복시 예외")
    public void fail_2() {
        List<Integer> lotto = List.of(1, 2, 3, 4, 5, 6);
        int bonus = 4;

        Assertions.assertThatThrownBy(() -> new WinningLotto(lotto, bonus))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("보너스 숫자는 당첨 번호와 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("로또 번호 개수 부족시 6이 아닌경우 예외")
    public void fail_3() {
        List<Integer> lotto = List.of(1, 2, 3, 5, 6);
        int bonus = 7;

        Assertions.assertThatThrownBy(() -> new WinningLotto(lotto, bonus))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("당첨번호는 6자리만 가능합니다.");
    }

    @Test
    @DisplayName("로또 번호가 숫자 범위에 속하지 않는 경우 예외")
    public void fail_4() {
        List<Integer> lotto = List.of(1, 2, 3, 4, 5, 6239);
        int bonus = 7;

        Assertions.assertThatThrownBy(() -> new WinningLotto(lotto, bonus))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("당첨 번호는 1~50 사이의 값만 가능합니다.");
    }

    @Test
    @DisplayName("보너스 번호가 범위에 속하지 않는 경우 예외")
    public void fail_5() {
        List<Integer> lotto = List.of(1, 2, 3, 4, 5, 6);
        int bonus = 123123;

        Assertions.assertThatThrownBy(() -> new WinningLotto(lotto, bonus))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("당첨 번호는 1~50 사이의 값만 가능합니다.");
    }
}
