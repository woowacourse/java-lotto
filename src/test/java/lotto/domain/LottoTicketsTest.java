package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import lotto.domain.generator.AutoLottoNumberGenerator;
import lotto.domain.generator.LottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {

    @DisplayName("로또 티켓의 갯수를 반환한다.")
    @Test
    void 로또_티켓_개수_확인() {
        // given
        int lottoCount = 14;
        LottoNumberGenerator lottoNumberGenerator = new AutoLottoNumberGenerator();

        // when
        LottoTickets lottoTickets = new LottoTickets(lottoCount, lottoNumberGenerator);

        // then
        assertThat(lottoTickets.totalCount()).isEqualTo(lottoCount);
    }
}