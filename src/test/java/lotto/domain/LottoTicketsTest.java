package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import lotto.utils.FixedLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {

    @Test
    @DisplayName("로또 티켓 발행 테스트")
    void getLottoTickets() {
        List<LottoTicket> lottoTickets = Arrays.asList(
            new FixedLottoGenerator().generateLottoTicket(),
            new FixedLottoGenerator().generateLottoTicket(),
            new FixedLottoGenerator().generateLottoTicket());

        assertThat(new LottoTickets(3, new FixedLottoGenerator()).getLottoTickets())
            .isEqualTo(lottoTickets);
    }

    @Test
    @DisplayName("로또 티켓 사이즈 테스트")
    void size() {
        assertThat(new LottoTickets(5, new FixedLottoGenerator()).size())
            .isEqualTo(5);
    }

    @Test
    @DisplayName("입력받은 값으로 로또 티켓 발행")
    void generate() {
        List<LottoTicket> lottoTicketsValue = Arrays.asList(
            new LottoTicket("11,12,13,14,15,16"),
            new LottoTicket("21,22,23,24,25,26"),
            new LottoTicket("31,32,33,34,35,36"));

        LottoTickets lottoTickets = new LottoTickets(lottoTicketsValue, 7,
            new FixedLottoGenerator());

        assertThat(lottoTickets.size()).isEqualTo(10);
    }

}