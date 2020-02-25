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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

        LottoMatchResultBundle expectedResultBundle = new LottoMatchResultBundle(Arrays.asList(new LottoMatchResult(Rank.FIRST)));

        //when
        LottoMatchResultBundle resultBundle = ticketBundle.createLottoMatchResultBundle(winLottoTicket);

        //then
        assertThat(resultBundle).isEqualTo(expectedResultBundle);
    }

    @DisplayName("예외 케이스 테스트: 생성자에 null이 입력된 경우 Exception 발생")
    @Test
    void name() {
        assertThatThrownBy(() -> new LottoTicketBundle(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("전달받은 값이 null 혹은 empty 입니다.");
    }
}
