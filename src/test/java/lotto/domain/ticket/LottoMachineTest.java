package lotto.domain.ticket;

import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {
    private static LottoMachine lottoMachine;

    @BeforeAll
    static void setUp() {
        lottoMachine = new LottoMachine();
    }

    @DisplayName("입력한 구입 갯수에 맞게 티켓을 발급하는지 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3,})
    void test1(int numberOfTickets) {
        List<LottoTicket> lottoTickets = lottoMachine.buyTickets(numberOfTickets);

        assertThat(lottoTickets).hasSize(numberOfTickets);
    }

    @DisplayName("우승 로또 만들기")
    @Test
    void makeWinLottoTicket() {
        //given
        Integer[] winNumbers = {1, 2, 3, 4, 5, 6};
        Set<LottoBall> winBalls = Arrays.stream(winNumbers)
                .map(LottoBallFactory::getLottoBallByNumber)
                .collect(Collectors.toSet());
        LottoBall bonusBall = LottoBallFactory.getLottoBallByNumber(7);

        WinLottoTicket expectedLotto = new WinLottoTicket(new LottoTicket(winBalls), bonusBall);

        //when
        WinLottoTicket winLottoTicket = lottoMachine.createWinLottoTicket(Arrays.asList(winNumbers), 7);

        //then
        assertThat(winLottoTicket).isEqualTo(expectedLotto);
    }

    @DisplayName("수동 입력 번호로 로또 티켓 생성 테스트")
    @Test
    void name() {
        //given
        List<Integer> manualNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        Set<LottoBall> manualBalls = manualNumbers.stream()
                .map(LottoBallFactory::getLottoBallByNumber)
                .collect(Collectors.toSet());

        LottoTicket expectedTicket = new LottoTicket(manualBalls);

        //when
        LottoTicket manualLotto = lottoMachine.createOneTicket(manualNumbers);

        //then
        assertThat(manualLotto).isEqualTo(expectedTicket);
    }
}
