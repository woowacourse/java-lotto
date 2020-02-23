package lotto;

import domain.LottoNumber;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
    @Test
    void 범위_밖의_로또_번호_생성_시_예외_발생() {
        assertThatThrownBy(() -> {
            LottoNumber.valueOf(0);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 숫자 범위를 넘어섰습니다.");
    }

    @Test
    void 숫자가_아닌_경우_예외_발생() {
        assertThatThrownBy(() -> {
            LottoNumber.valueOf("a");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("로또 넘버는 숫자여야 합니다. 입력한 문자 : %s", "a"));
    }

    @Test
    void String_숫자로_로또_넘버_생성() {
        assertThat(LottoNumber.valueOf("1")).isEqualTo(LottoNumber.valueOf(1));
    }
}
