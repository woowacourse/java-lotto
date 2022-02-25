package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketTest {

    @Test
    @DisplayName("로또 티켓을 위한 로또 번호들이 저장되는지 확인한다.")
    void generateLottoTicket_Test() {
        final GenerateStrategy generateStrategy = () -> Arrays.asList(1, 2, 3, 4, 5, 6);
        final LottoTicket lottoTicket = new LottoTicket(generateStrategy);

        assertThat(lottoTicket.lottoNumbers()).isEqualTo(Arrays.asList(1, 2, 3, 4, 5, 6));
    }
}