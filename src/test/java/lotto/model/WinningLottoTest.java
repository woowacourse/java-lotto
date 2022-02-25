package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    private WinningLotto winningLotto;

    @Test
    void 당첨로또_생성_테스트_정상() {
        winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
        assertThat(winningLotto).isInstanceOf(WinningLotto.class);
    }

    @Test
    void 당첨로또_생성_테스트_길이() {
        assertThatThrownBy(() ->
                winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5), 7))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 당첨로또_생성_테스트_범위() {
        assertThatThrownBy(() ->
                winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 46), 7))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 당첨로또_생성_테스트_중복() {
        assertThatThrownBy(() ->
                winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 4), 7))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 당첨로또_생성_테스트_보너스번호_중복() {
        assertThatThrownBy(() ->
                winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5), 1))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }
}
