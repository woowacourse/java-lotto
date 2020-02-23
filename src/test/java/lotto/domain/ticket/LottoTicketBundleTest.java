package lotto.domain.ticket;

import lotto.domain.result.LottoResult;
import lotto.domain.result.LottoResultBundle;
import lotto.domain.result.win.WinningLotto;
import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;
import lotto.view.dto.BettingMoneyDTO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketBundleTest {

    @Test
    void getLottoResults() {
        //given
        LottoTicketBundle ticketBundle = new LottoTicketBundle(new LottoMachineForTest().buyTickets(new BettingMoneyDTO(1000)));

        Set<LottoBall> winningNumbers = Arrays.stream(new int[]{1, 2, 3, 4, 5, 6})
                .mapToObj(LottoBallFactory::getLottoBallByNumber)
                .collect(Collectors.toSet());
        LottoBall bonusNumber = LottoBallFactory.getLottoBallByNumber(7);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        LottoResultBundle expectedResultBundle = new LottoResultBundle(Arrays.asList(new LottoResult(6, false)));

        //when
        LottoResultBundle resultBundle = ticketBundle.createLottoResultBundle(winningLotto);
        resultBundle.toString();

        //then
        assertThat(resultBundle).isEqualTo(expectedResultBundle);
    }
}
