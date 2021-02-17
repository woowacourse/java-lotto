package lotto.domain.lottoData;

import lotto.utils.RedundantNumbersException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {
    @Test
    void 객체_생성() {
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6", "7");
        assertThat(winningLotto).isEqualTo(new WinningLotto("1, 2, 3, 4, 5, 6", "7"));
    }

    @Test
    void 중복_체크() {
        assertThatThrownBy(() -> {
            new WinningLotto("1, 2, 3, 4, 6, 6", "7");
        }).isInstanceOf(RedundantNumbersException.class);
        assertThatThrownBy(() -> {
            new WinningLotto("1, 2, 3, 4, 5, 6", "6");
        }).isInstanceOf(RedundantNumbersException.class);
    }
}
