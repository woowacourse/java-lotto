package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {

    @DisplayName("구입 금액 및 수동 티켓 개수에 따른 수동 로또, 자동 로또가 제대로 생성되는지 확인")
    @Test
    void makeLottoTicketsByLottoMachine() {
        Money money = new Money(5000);
        PurchasingCounts purchasingCounts = PurchasingCounts.of(money, 2);
        List<List<Integer>> numberGroup = new ArrayList<>();
        numberGroup.add(Arrays.asList(1, 2, 3, 4, 5, 6));
        numberGroup.add(Arrays.asList(7, 8, 9, 10, 11, 12));
        LottoMachine lottoMachine = new LottoMachine(new ManualLottoNumberGenerator(numberGroup),
                new RandomLottoNumberGenerator());

        LottoTickets lottoTickets = lottoMachine.buyLottoTickets(purchasingCounts);

        assertThat(lottoTickets.size()).isEqualTo(5);
    }

    @DisplayName("수동 티켓 개수가 0개 일때 자동 티켓 개수 확인")
    @Test
    void makeAutoLottoTicketsIfManualTicketCountsZero() {
        Money money = new Money(5000);
        PurchasingCounts purchasingCounts = PurchasingCounts.of(money, 0);
        List<List<Integer>> numberGroup = new ArrayList<>();
        LottoMachine lottoMachine = new LottoMachine(new ManualLottoNumberGenerator(numberGroup),
                new RandomLottoNumberGenerator());

        LottoTickets lottoTickets = lottoMachine.buyLottoTickets(purchasingCounts);

        assertThat(lottoTickets.size()).isEqualTo(5);
    }

    @DisplayName("자동 티켓 개수가 0개 일때 수동 티켓 개수 확인")
    @Test
    void makeManualLottoTicketsIfAutoTicketCountsZero() {
        Money money = new Money(2000);
        PurchasingCounts purchasingCounts = PurchasingCounts.of(money, 2);
        List<List<Integer>> numberGroup = new ArrayList<>();
        numberGroup.add(Arrays.asList(1, 2, 3, 4, 5, 6));
        numberGroup.add(Arrays.asList(7, 8, 9, 10, 11, 12));
        LottoMachine lottoMachine = new LottoMachine(new ManualLottoNumberGenerator(numberGroup),
                new RandomLottoNumberGenerator());

        LottoTickets lottoTickets = lottoMachine.buyLottoTickets(purchasingCounts);

        assertThat(lottoTickets.size()).isEqualTo(2);
    }
}