package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
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
        assertDoesNotThrow(() -> new LottoTickets(lottoCount, lottoNumberGenerator));
    }

    @DisplayName("당첨 번호를 전달 받아 판별하여 로또 결과를 반환한다.")
    @Test
    void 당첨_번호_판별() {
        // given
        LottoTickets lottoTickets = new LottoTickets(1, size -> List.of(1, 2, 3, 4, 5, 6));
        WinningNumber winningNumber = new WinningNumber(new LottoTicket(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(7));

        // when
        LottoResult lottoResult = lottoTickets.determine(winningNumber);

        // then
        assertThat(lottoResult.getRanks().get(Rank.FIRST)).isEqualTo(1);
    }
}