package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.number.Payout;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoTicketTest {

    @ParameterizedTest
    @DisplayName("지불 금액에 따른 로또 개수")
    @CsvSource({"1,0", "999,0", "1000,1", "1001,1", "1999,1"})
    void valueOf(String amount, int expected) {
        LottoTicket lottoTicket = LottoTicket.valueOf(Payout.valueOf(amount),
            RandomLottoGenerator.getInstance());

        assertThat(lottoTicket.getCount()).isEqualTo(expected);
    }
}