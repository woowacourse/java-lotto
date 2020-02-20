package lotto;

import lotto.domain.LottoNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {
    @Test
    void 로또숫자가_최소값보다_작을_경우_예외_발생() {
        Assertions.assertThatThrownBy(() -> {
            new LottoNumber(0);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 숫자는 1~45사이어야 합니다.");
    }

    @Test
    void 로또숫자가_최대값보다_작을_경우_예외_발생() {
        Assertions.assertThatThrownBy(() -> {
            new LottoNumber(46);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 숫자는 1~45사이어야 합니다.");
    }
}
