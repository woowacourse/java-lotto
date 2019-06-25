package lotto.domain.machine;

import lotto.domain.ticket.LottoNumber;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTickets;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VendingMachineTest {
    @Test
    public void 매뉴얼_갯수_초과_예외() {
        VendingMachine vendingMachine = new VendingMachine(Money.of(5000));
        assertThrows(IllegalArgumentException.class, () -> {
            vendingMachine.createPurchase(5, Arrays.asList(Arrays.asList(1, 2, 3, 4, 5, 6)));
        });
    }

    @Test
    public void 자동_로또_생성_확인() {
        VendingMachine vendingMachine = new VendingMachine(Money.of(5000));
        Purchase purchase = vendingMachine.createPurchase(0, Arrays.asList());
        LottoTickets lottoTickets = vendingMachine.createLotto(purchase);
        assertThat(lottoTickets.lottoTicketsSize()).isEqualTo(5);
    }

    @Test
    public void 수동_로또_생성_확인() {
        VendingMachine vendingMachine = new VendingMachine(Money.of(1000));
        Purchase purchase = vendingMachine.createPurchase(1, Arrays.asList(Arrays.asList(1, 2, 3, 4, 5, 6)));
        LottoTickets lottoTickets = vendingMachine.createLotto(purchase);
        assertThat(lottoTickets.lottoTicketsSize()).isEqualTo(1);
    }

    @Test
    public void 수동_로또_생성_번호_확인() {
        VendingMachine vendingMachine = new VendingMachine(Money.of(1000));
        Purchase purchase = vendingMachine.createPurchase(1, Arrays.asList(Arrays.asList(1, 2, 3, 4, 5, 6)));
        LottoTickets lottoTickets = vendingMachine.createLotto(purchase);
        assertThat(lottoTickets.getIdxLottoTicket(0)).isEqualTo(new LottoTicket(Arrays.asList(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6))));
    }
}