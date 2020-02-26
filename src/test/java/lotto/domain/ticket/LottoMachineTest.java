package lotto.domain.ticket;

import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoMachineTest {
    private static LottoMachine lottoMachine;

    @BeforeAll
    static void setUp() {
        lottoMachine = new LottoMachine();
    }

    @DisplayName("가격에 해당하는 티켓을 발급하는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"1000,1", "1500,1", "2000,2"})
    void test1(int money, int expect) {
        List<LottoTicket> lottoTickets = lottoMachine.buyTickets(money);

        assertThat(lottoTickets).hasSize(expect);
    }

    @DisplayName("예외 테스트: 로또 한 장 가격보다 작은 값 입력시 Exception 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 999})
    void test1(int money) {
        assertThatThrownBy(() -> lottoMachine.buyTickets(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("%d는 최소 구매 금액보다 작습니다.", money);
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
        LottoTicket ticketFromManualMachine = lottoMachine.createOneTicket(new HashSet<>(manualNumbers));

        //then
        assertThat(ticketFromManualMachine).isEqualTo(expectedTicket);
    }
}
