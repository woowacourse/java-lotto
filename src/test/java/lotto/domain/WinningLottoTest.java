package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoTest {
    @Test
    void 객체가_하나만_생성되는지_확인() {
        String input = "1,2,3,4,5,6";

        assertThat(WinningLotto.getInstance(input) == WinningLotto.getInstance(input));
    }

    @Test
    void 요청한_번호와_일치하는_당첨번호_boolean_리턴() {
        String input = "1,2,3,4,5,6";
        WinningLotto winningLotto = WinningLotto.getInstance(input);

        assertThat(winningLotto.hasEqualNumber(LottoNumber.getNumber(4))).isTrue();
        assertThat(winningLotto.hasEqualNumber(LottoNumber.getNumber(9))).isFalse();
    }
}
