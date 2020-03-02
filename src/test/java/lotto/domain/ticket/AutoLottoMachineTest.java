package lotto.domain.ticket;

import lotto.domain.customer.Customer;
import lotto.domain.customer.PurchaseInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoLottoMachineTest {
    @DisplayName("자동 로또 머신: 입력한 구입 갯수에 맞게 티켓을 발급하는지 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 3000})
    void test1(int amount) {
        LottoMachine autoMachine = new AutoLottoMachine();
        Customer customer = new Customer(new PurchaseInfo(amount, 0));

        List<LottoTicket> lottoTickets = autoMachine.buyTickets(customer);

        assertThat(lottoTickets).hasSize(amount / 1000);
    }
}
