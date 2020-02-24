package lotto.domain.ticket;

import lotto.domain.result.LottoResult;
import lotto.domain.result.LottoResultBundle;
import lotto.domain.result.rank.Rank;
import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketBundleTest {

    @DisplayName("winLottoTicket로 결과 번들 생성 테스트")
    @Test
    void createLottoResultBundle() {
        //given
        LottoTicketBundle ticketBundle = new LottoTicketBundle(new LottoMachineForTest().buyTickets(1000));

        Set<LottoBall> winBalls = Arrays.stream(new int[]{1, 2, 3, 4, 5, 6})
                .mapToObj(LottoBallFactory::getLottoBallByNumber)
                .collect(Collectors.toSet());
        LottoBall bonusBall = LottoBallFactory.getLottoBallByNumber(7);
        WinLottoTicket winLottoTicket = new WinLottoTicket(new LottoTicket(winBalls), bonusBall);

        LottoResultBundle expectedResultBundle = new LottoResultBundle(Arrays.asList(new LottoResult(Rank.FIRST)));

        //when
        LottoResultBundle resultBundle = ticketBundle.createLottoResultBundle(winLottoTicket);

        //then
        assertThat(resultBundle).isEqualTo(expectedResultBundle);
    }
}
