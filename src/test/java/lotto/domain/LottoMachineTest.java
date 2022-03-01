package lotto.domain;

import lotto.fixture.LottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoMachineTest {

    @Test
    @DisplayName("자동 로또 티켓을 생성한다")
    void makeLottoTicketsAuto() {
        LottoMachine lottoMachine = new LottoMachine(14, 0);
        List<Lotto> manualLottos = List.of();

        List<Lotto> lottoTickets = lottoMachine.makeLottoTickets(manualLottos);
        assertThat(lottoTickets.size()).isEqualTo(14);
    }

    @Test
    @DisplayName("수동 로또는 전체 로또 개수를 넘어설 수 없다")
    void throwExceptionWhenManualCountOverTotalCount() {
        assertThatThrownBy(() -> new LottoMachine(1, 2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수동 로또 리스트를 입력받아 전체 로또 리스트를 생성한다. 전체 로또 개수에서 수동 로또 개수를 뺀만큼 자동 로또를 추가한다")
    void makeLottoTickets() {
        int totalTicketCount = 5;
        int manualTicketCount = 2;
        LottoMachine lottoMachine = new LottoMachine(totalTicketCount, manualTicketCount);

        List<Lotto> manualLottos = new ArrayList<>();
        manualLottos.add(LottoGenerator.valueOf(1, 2, 3, 4, 5, 6));
        manualLottos.add(LottoGenerator.valueOf(1, 2, 3, 4, 5, 6));

        List<Lotto> lottos = lottoMachine.makeLottoTickets(manualLottos);

        assertThat(lottos.size()).isEqualTo(totalTicketCount);
    }
}
