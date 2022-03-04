package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.strategy.NumberGenerateStrategy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {
    private final Set<Integer> dummyLottoNumber = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    private final List<Set<Integer>> selfTicketNumbers = List.of(Set.of(1, 2, 3, 4, 5, 6));
    private final NumberGenerateStrategy numberGenerateStrategy = () -> new HashSet<>(dummyLottoNumber);

    @Test
    @DisplayName("음수의 금액으로 로또 티켓을 생성하려할 시 에러를 발생시키는지 검사한다.")
    void checkNegativeMoney() {
        int purchaseMoney = -17000;
        assertThatThrownBy(() -> LottoTickets.generateAutoTickets(new LottoMoney(purchaseMoney), numberGenerateStrategy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoMoney.NOT_POSITIVE_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("로또 티켓이 정상적으로 자동 생성됐는지 검사한다.")
    void autoLottoTicketsTest() {
        int purchaseMoney = 17000;
        LottoTickets lottoTickets = LottoTickets.generateAutoTickets(new LottoMoney(purchaseMoney), numberGenerateStrategy);
        lottoTickets.getTickets()
                .forEach(lottoTicket -> assertThat(lottoTicket.getLottoNumberValues()).isEqualTo(dummyLottoNumber));
    }

    @Test
    @DisplayName("로또 티켓이 정상적으로 수동 생성됐는지 검사한다.")
    void selfLottoTicketsTest() {
        LottoTickets lottoTickets = LottoTickets.fromTicketNumbers(selfTicketNumbers);
        lottoTickets.getTickets()
                .forEach(lottoTicket -> assertThat(lottoTicket.getLottoNumberValues()).isEqualTo(dummyLottoNumber));
    }

    @Test
    @DisplayName("로또 티켓이 가격에 맞게 입력된 금액에 맞춰 티켓을 만드는지 검사한다.")
    void checkLottoTicketsCount() {
        int purchaseMoney = 10000;
        LottoTickets lottoTickets = LottoTickets.generateAutoTickets(new LottoMoney(purchaseMoney), numberGenerateStrategy);
        assertThat(lottoTickets.getTickets().size()).isEqualTo(10);
    }

    @Test
    @DisplayName("로또 티켓이 잘 더해지는지 확인한다.")
    void concatTest() {
        int purchaseMoney = 1000;
        LottoTickets self = LottoTickets.fromTicketNumbers(selfTicketNumbers);
        LottoTickets auto = LottoTickets.generateAutoTickets(new LottoMoney(purchaseMoney), numberGenerateStrategy);
        LottoTickets all = self.concat(auto);
        assertThat(all.getTickets().size()).isEqualTo(self.getTickets().size() + auto.getTickets().size());
        assertThat(all.getSelfPurchaseCount()).isEqualTo(self.getSelfPurchaseCount() + auto.getSelfPurchaseCount());
    }
}