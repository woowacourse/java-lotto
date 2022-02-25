package model.lottotickets;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import model.lottonumbergenerator.Generator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketTest {

    @Test
    @DisplayName("로또 티켓을 위한 로또 번호들이 저장되는지 확인한다.")
    void generateLottoTicket_Test() {
        final Generator generator = () -> Arrays.asList(1, 2, 3, 4, 5, 6);
        final LottoTicket lottoTicket = new LottoTicket(generator);

        assertThat(lottoTicket.lottoNumbers()).isEqualTo(Arrays.asList(1, 2, 3, 4, 5, 6));
    }
}