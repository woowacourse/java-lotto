package lotto.domain.ticket;

import lotto.domain.result.LottoMatchResult;
import lotto.domain.result.LottoMatchResultBundle;
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
        Set<LottoBall> lottoBalls = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6})
                .map(LottoBallFactory::getLottoBallByNumber)
                .collect(Collectors.toSet());
        LottoBall bonusBall = LottoBallFactory.getLottoBallByNumber(7);

        LottoTicketBundle ticketBundle = new LottoTicketBundle(Arrays.asList(new LottoTicket(lottoBalls)));

        WinLottoTicket winLottoTicket = new WinLottoTicket(new LottoTicket(lottoBalls), bonusBall);

        LottoMatchResultBundle expectedResultBundle = new LottoMatchResultBundle(Arrays.asList(new LottoMatchResult(Rank.FIRST)));

        //when
        LottoMatchResultBundle resultBundle = ticketBundle.createLottoMatchResultBundle(winLottoTicket);

        //then
        assertThat(resultBundle).isEqualTo(expectedResultBundle);
    }
}
