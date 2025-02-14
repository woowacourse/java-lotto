package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

    @DisplayName("로또 구입 금액만큼 로또 티켓 수 생성 확인")
    @Test
    public void lottoTicketAmount() {
        LottoMoney lottoMoney = new LottoMoney("10000");
        LottoMachine lottoMachine = new LottoMachine(lottoMoney);
        assertThat(lottoMachine.getLottoTickets().size()).isEqualTo(10);
    }

    @DisplayName("로또 번호가 중복 없이 6개 생성 확인")
    @Test
    public void lottoTicketNumbers() {
        LottoMoney lottoMoney = new LottoMoney("1000");
        LottoMachine lottoMachine = new LottoMachine(lottoMoney);
        lottoMachine.getLottoTickets().forEach(lottoTickets -> {
            assertThat(lottoTickets.getLotto()).hasSize(6);
            assertThat(lottoTickets.getLotto()).doesNotHaveDuplicates();
        });
    }
}
