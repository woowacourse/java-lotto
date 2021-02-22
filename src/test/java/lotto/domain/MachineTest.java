package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.utils.FixedLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MachineTest {
    @Test
    @DisplayName("구입 금액에 맞는 티켓 장수 확인")
    void buyTickets() {
        assertThat(new Machine("4000", new FixedLottoGenerator()).getTickets())
            .size().isEqualTo(4);
    }

    @Test
    @DisplayName("구입 금액이 나누어 떨어지지 않는 경우 최대 티켓 장수 확인")
    void buyTickets2() {
        assertThat(new Machine("4100", new FixedLottoGenerator()).getTickets())
            .size().isEqualTo(4);
    }

    @Test
    @DisplayName("결과 값 테스트")
    void getResult() {
        final LottoTickets lottoTickets = new LottoTickets(3, new FixedLottoGenerator());
        Result result = new Result("1,2,3,4,5,6", "7", lottoTickets);
        Machine machine = new Machine("3000", new FixedLottoGenerator());

        assertThat(machine.getResult("1,2,3,4,5,6", "7")).isEqualTo(result);
    }

}