package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AutoLottoMachineTest {
    private AutoLottoMachine autoLottoMachine = new AutoLottoMachine();

    @DisplayName("목표 장수에 맞추어 티켓을 생성하는지 확인")
    @Test
    void 목표에_맞추어_티켓을_생성한다(){
        int numberOfTickets = 5;

        LottoTickets tickets = autoLottoMachine.createTickets(5);

        assertThat(tickets.size()).isEqualTo(numberOfTickets);
    }

    @DisplayName("금액에 맞추어 티켓을 생성하는지 확인")
    @Test
    void 금액에_맞추어_티켓을_생성하는지(){
        int numberOfTickets = 5;
        int purchaseMoney = 5000;

        LottoTickets tickets = autoLottoMachine.createTicketsByMoney(purchaseMoney);

        assertThat(tickets.size()).isEqualTo(numberOfTickets);
    }

    @DisplayName("구매금액 단위가 맞지 않으면 예외처리하는지 확인")
    @ParameterizedTest
    @ValueSource(ints = {5500, 4900, 1234, 2500})
    void 금액단위_안맞으면_예외(int purchaseMoney) {
        assertThatThrownBy(() -> autoLottoMachine.createTicketsByMoney(purchaseMoney))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구매금액 단위가 음수이면 예외처리하는지 확인")
    @ParameterizedTest
    @ValueSource(ints = {-1000, -2000, -50000, -150000})
    void 금액이_음수이면_예외(int purchaseMoney){
        assertThatThrownBy(() -> autoLottoMachine.createTicketsByMoney(purchaseMoney))
            .isInstanceOf(IllegalArgumentException.class);
    }
}