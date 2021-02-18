package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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

}