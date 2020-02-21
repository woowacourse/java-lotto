package lotto.domain.ticket;

import lotto.domain.result.win.WinningLotto;
import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoFactory;
import lotto.view.dto.BettingMoneyRequestDTO;
import lotto.view.dto.WinningLottoRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class LottoCompanyTest {

    @DisplayName("가격에 해당하는 티켓을 발급하는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"1000,1", "1500,1", "2000,2"})
    void test1(int money, int expect) {
        LottoCompany lottoCompany = new RealLottoCompany();
        List<LottoTicket> lottoTickets = lottoCompany.buyTicket(new BettingMoneyRequestDTO(money));

        assertThat(lottoTickets).hasSize(expect);
    }

    @DisplayName("우승 로또 만들기")
    @Test
    void makeWinningLotto() {
        //given
        LottoCompany lottoCompany = new RealLottoCompany();
        Set<LottoBall> lottoBalls = aLottoBalls(1, 2, 3, 4, 5, 6);
        LottoBall bonusBall = LottoFactory.getLottoBallByNumber(7);
        WinningLotto expectedLotto = new WinningLotto(lottoBalls, bonusBall);

        WinningLottoRequestDTO winningLottoRequestDTO = new WinningLottoRequestDTO("1,2,3,4,5,6", 7);

        //when
        WinningLotto winningLotto = lottoCompany.makeWinningLotto(winningLottoRequestDTO);

        //then
        assertThat(winningLotto).isEqualTo(expectedLotto);
    }

    @DisplayName("테스트용 고정 번호 로또 티켓 생성하기")
    @Test
    void test2() {
        //given
        LottoCompany testLottoCompany = new TestLottoCompany();
        BettingMoneyRequestDTO bettingMoneyRequestDTO = new BettingMoneyRequestDTO(1000);

        LottoTicket expectedTicket = new LottoTicket(aLottoBalls(1, 2, 3, 4, 5, 6));

        //when
        List<LottoTicket> tickets = testLottoCompany.buyTicket(bettingMoneyRequestDTO);

        //then
        assertThat(tickets.get(0)).isEqualTo(expectedTicket);
    }

    private Set<LottoBall> aLottoBalls(int... numbers) {
        return Arrays.stream(numbers)
                .mapToObj(LottoFactory::getLottoBallByNumber)
                .collect(Collectors.toSet());
    }
}
