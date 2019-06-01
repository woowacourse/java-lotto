package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    @Test
    void 당첨번호_생성() {
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6", 7);
        assertThat(winningLotto).isEqualTo(new WinningLotto("1,2,3,4,5,6", 7));
    }
    @Test
    void 중복숫자_생성() {
        assertThrows(IllegalArgumentException.class, () -> new WinningLotto("1,2,3,4,5,5", 7));
    }

    @Test
    void 보너스볼_중복() {
        assertThrows(IllegalArgumentException.class, () -> new WinningLotto("1,2,3,4,5,6", 6));
    }
}
