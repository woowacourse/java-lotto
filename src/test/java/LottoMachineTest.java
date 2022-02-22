import domain.LottoMachine;
import domain.LottoTickets;
import domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


class LottoMachineTest {

    @Test
    @DisplayName("입력 금액이 1000원 미만일 때 예외")
    void insertAmountBelowThousand() {
        LottoMachine lottoMachine = new LottoMachine();

        assertThatThrownBy(() -> {
            lottoMachine.purchaseLottoTickets(Money.from(900));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("입력 금액에 따라 알맞은 개수의 로또 생성 검증")
    void createLottoTicketsByAmount() {
        LottoMachine lottoMachine = new LottoMachine();

        LottoTickets lottoTickets = lottoMachine.purchaseLottoTickets(Money.from(10000));
        assertThat(lottoTickets.size()).isEqualTo(10);
    }
}
