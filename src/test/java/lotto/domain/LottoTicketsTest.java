package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
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
        assertDoesNotThrow(() -> LottoTickets.createAutoLottoTickets(lottoCount, lottoNumberGenerator));
    }

    @DisplayName("수동 구매한 로또 개수와 수동 번로를 받아 로또 티켓 리스트를 생성한다.")
    @Test
    void 로또_티켓_수동_발급() {
        // given
        int lottoCount = 3;
        List<List<Integer>> manualNumbers = List.of(
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44));

        // when & then
        assertDoesNotThrow(() -> LottoTickets.createManualLottoTickets(lottoCount, manualNumbers));
    }

    @DisplayName("로또 티켓 여러장을 합쳐 함께 관리할 수 있다.")
    @Test
    void 로또_티켓_합침() {
        // given
        LottoTickets autoLottoTickets = LottoTickets.createAutoLottoTickets(10, new AutoLottoNumberGenerator());
        LottoTickets manualLottoTickets = LottoTickets.createManualLottoTickets(1,
                List.of(List.of(8, 21, 23, 41, 42, 43)));

        // when
        LottoTickets totalLottoTickets = autoLottoTickets.combine(manualLottoTickets);

        // then
        assertThat(totalLottoTickets.getLottoTickets().size()).isEqualTo(11);
    }

    @DisplayName("당첨 번호를 전달 받아 판별하여 로또 결과를 반환한다.")
    @Test
    void 당첨_번호_판별() {
        // given
        LottoTickets lottoTickets = LottoTickets.createAutoLottoTickets(1, size -> List.of(1, 2, 3, 4, 5, 6));
        WinningNumber winningNumber = new WinningNumber(new LottoTicket(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(7));

        // when
        LottoResult lottoResult = lottoTickets.determine(winningNumber);

        // then
        assertThat(lottoResult.getRanks().get(Rank.FIRST)).isEqualTo(1);
    }
}