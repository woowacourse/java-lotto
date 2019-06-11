package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
    @Test
    public void 범위_내의_숫자가_입력되었을_때() {
        assertThat(new LottoNumber(1)).isExactlyInstanceOf(LottoNumber.class);
    }

    @Test
    public void 범위보다_작은_숫자가_입력되었을_때() {
        assertThatThrownBy(() -> {
            new LottoNumber(0);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 범위보다_큰_숫자가_입력되었을_때() {
        assertThatThrownBy(() -> {
            new LottoNumber(46);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
