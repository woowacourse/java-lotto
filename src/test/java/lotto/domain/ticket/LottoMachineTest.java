package lotto.domain.ticket;

import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {

    @DisplayName("자동 로또 머신: 입력한 구입 갯수에 맞게 티켓을 발급하는지 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void test1(int numberOfTickets) {
        LottoMachine autoMachine = new AutoLottoMachine();

        List<LottoTicket> lottoTickets = autoMachine.buyTickets(numberOfTickets);

        assertThat(lottoTickets).hasSize(numberOfTickets);
    }

    @DisplayName("수동 로또 머신: 입력한 구입 갯수에 맞게 티켓을 발급하는지 테스트")
    @Test
    void test2() {
        List<Integer> manualNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoMachine manualMachine = new ManualLottoMachine(new ArrayList<>(Arrays.asList(manualNumbers)));

        List<LottoTicket> lottoTickets = manualMachine.buyTickets(1);

        assertThat(lottoTickets).hasSize(1);
    }


    @DisplayName("우승 로또 만들기")
    @Test
    void makeWinLottoTicket() {
        //given
        LottoMachine autoMachine = new AutoLottoMachine();

        Integer[] winNumbers = {1, 2, 3, 4, 5, 6};
        Set<LottoBall> winBalls = Arrays.stream(winNumbers)
                .map(LottoBallFactory::getLottoBallByNumber)
                .collect(Collectors.toSet());
        LottoBall bonusBall = LottoBallFactory.getLottoBallByNumber(7);

        WinLottoTicket expectedLotto = new WinLottoTicket(new LottoTicket(winBalls), bonusBall);

        //when
        WinLottoTicket winLottoTicket = autoMachine.createWinLottoTicket(Arrays.asList(winNumbers), 7);

        //then
        assertThat(winLottoTicket).isEqualTo(expectedLotto);
    }

    @DisplayName("수동 입력 번호로 로또 티켓 생성 테스트")
    @Test
    void name() {
        //given
        List<Integer> manualNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        LottoMachine manualMachine = new ManualLottoMachine(new ArrayList<>(Arrays.asList(manualNumbers)));

        Set<LottoBall> manualBalls = manualNumbers.stream()
                .map(LottoBallFactory::getLottoBallByNumber)
                .collect(Collectors.toSet());

        LottoTicket expectedTicket = new LottoTicket(manualBalls);

        //when
        LottoTicket manualLotto = manualMachine.createOneTicket();

        //then
        assertThat(manualLotto).isEqualTo(expectedTicket);
    }
}
