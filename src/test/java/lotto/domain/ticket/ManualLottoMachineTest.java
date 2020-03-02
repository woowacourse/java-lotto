package lotto.domain.ticket;

import lotto.domain.customer.Customer;
import lotto.domain.customer.PurchaseInfo;
import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ManualLottoMachineTest {
    @DisplayName("수동 로또 머신: 입력한 구입 갯수에 맞게 티켓을 발급하는지 테스트")
    @Test
    void test2() {
        LottoMachine manualMachine = new ManualLottoMachine();
        Customer customer = new Customer(new PurchaseInfo(3000, 1), Arrays.asList(Arrays.asList(1, 2, 3, 4, 5, 6)));

        List<LottoTicket> lottoTickets = manualMachine.buyTickets(customer);

        assertThat(lottoTickets).hasSize(1);
    }

    @DisplayName("수동 입력 번호로 로또 티켓 생성 테스트")
    @Test
    void name() {
        //given
        LottoMachine manualMachine = new ManualLottoMachine();
        List<Integer> manualNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Customer customer = new Customer(new PurchaseInfo(1000, 1), Arrays.asList(manualNumbers));

        Set<LottoBall> manualBalls = manualNumbers.stream()
                .map(LottoBallFactory::getLottoBallByNumber)
                .collect(Collectors.toSet());

        LottoTicket expectedTicket = new LottoTicket(manualBalls);

        //when
        List<LottoTicket> lottoTickets = manualMachine.buyTickets(customer);

        //then
        assertThat(lottoTickets.get(0)).isEqualTo(expectedTicket);
    }
}
