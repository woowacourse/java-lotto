package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import lotto.domain.generator.AutoLottoNumberGenerator;
import lotto.domain.generator.LottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {

    @DisplayName("로또 생성 갯수와 생성 전략을 받아 로또 티켓 리스트를 생성한다.")
    @Test
    void 로또_티켓_정상_생성() {
        // given
        int lottoCount = 14;
        LottoNumberGenerator lottoNumberGenerator = new AutoLottoNumberGenerator();

        // when & then
        assertThatCode(() -> new LottoTickets(lottoCount, lottoNumberGenerator))
                .doesNotThrowAnyException();
    }
}