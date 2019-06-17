package lotto.domain.lottoresult;

import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTicketGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LottoResultTest {
    private LottoTicketGroup lottoTickets;
    private LottoResult lottoResult;

    @BeforeEach
    void setup() {
        lottoTickets = new LottoTicketGroup(Arrays.asList(
                LottoTicket.create(() -> Arrays.asList(1, 2, 3, 4, 5, 6)),
                LottoTicket.create(() -> Arrays.asList(1, 2, 3, 7, 8, 9))
        ));
        lottoResult = lottoTickets.match(
                WinningLotto.create("4,5,6,7,8,9", "15")
        );
    }

    @Test
    void 수익률_확인() {
        assertThat(lottoResult.getEarningRate())
                .isEqualTo(new BigDecimal(500.000));
    }
}
