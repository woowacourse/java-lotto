package lotto.view;

import lotto.domain.LottoSeller;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InputViewTest {
    @Test
    void 가격을_입력하면_LottoSeller를_반환하는_메소드() {
        assertThat(InputView.makeLottoSeller("1000")).isInstanceOf(LottoSeller.class);
    }
}