package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import lotto.domain.generator.AutoLottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketTest {

    @DisplayName("로또 티켓 생성 시점에 로또 번호 생성을 위한 전략을 활용한다.")
    @Test
    void 로또_티켓_정상_생성() {
        // given & when & then
        assertThatCode(() -> new LottoTicket(new AutoLottoNumberGenerator()))
                .doesNotThrowAnyException();
    }
}