package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.strategy.NumberGenerateStrategy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {

    @Test
    @DisplayName("로또 티켓이 정상적으로 자동 생성됐는지 검사한다.")
    void autoLottoTicketsTest() {
        Set<Integer> dummyLottoNumber = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        NumberGenerateStrategy numberGenerateStrategy = () -> new HashSet<>(dummyLottoNumber);
        int purchaseMoney = 17000;
        LottoTickets lottoTickets = LottoTickets.generateAutoTickets(new LottoMoney(purchaseMoney),
                numberGenerateStrategy);
        lottoTickets.getTickets()
                .forEach(lottoTicket -> assertThat(lottoTicket.getLottoNumberValues()).isEqualTo(dummyLottoNumber));
    }

    @Test
    @DisplayName("로또 티켓이 정상적으로 수동 생성됐는지 검사한다.")
    void selfLottoTicketsTest() {
        Set<Integer> dummyLottoNumber = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Set<Integer>> selfTicketNumbers = List.of(Set.of(1, 2, 3, 4, 5, 6));
        LottoTickets lottoTickets = LottoTickets.fromTicketNumbers(selfTicketNumbers);
        lottoTickets.getTickets()
                .forEach(lottoTicket -> assertThat(lottoTicket.getLottoNumberValues()).isEqualTo(dummyLottoNumber));
    }

    @Test
    @DisplayName("로또 티켓이 가격에 맞게 입력된 금액에 맞춰 티켓을 만드는지 검사한다.")
    void checkLottoTicketsCount() {
        Set<Integer> dummyLottoNumber = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        NumberGenerateStrategy numberGenerateStrategy = () -> new HashSet<>(dummyLottoNumber);
        int purchaseMoney = 10000;
        LottoTickets lottoTickets = LottoTickets.generateAutoTickets(new LottoMoney(purchaseMoney),
                numberGenerateStrategy);
        assertThat(lottoTickets.getTickets().size()).isEqualTo(10);
    }

    @Test
    @DisplayName("로또 티켓이 잘 더해지는지 확인한다.")
    void concatTest() {
        Set<Integer> dummyLottoNumber = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Set<Integer>> selfTicketNumbers = List.of(Set.of(1, 2, 3, 4, 5, 6));
        NumberGenerateStrategy numberGenerateStrategy = () -> new HashSet<>(dummyLottoNumber);
        int purchaseMoney = 1000;
        LottoTickets self = LottoTickets.fromTicketNumbers(selfTicketNumbers);
        LottoTickets auto = LottoTickets.generateAutoTickets(new LottoMoney(purchaseMoney), numberGenerateStrategy);
        LottoTickets all = self.concat(auto);
        assertThat(all.getTickets().size()).isEqualTo(self.getTickets().size() + auto.getTickets().size());
        assertThat(all.getSelfPurchaseCount()).isEqualTo(self.getSelfPurchaseCount() + auto.getSelfPurchaseCount());
    }
}