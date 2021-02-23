package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.ManualCount;
import lotto.domain.number.Payout;
import lotto.domain.rank.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoTicketTest {

    @ParameterizedTest
    @DisplayName("지불 금액에 따른 로또 개수")
    @CsvSource({"1,0", "999,0", "1000,1", "1001,1", "1999,1"})
    void valueOf(String amount, int expected) {
        LottoTicket lottoTicket = LottoExchange.getInstance()
            .buyLottoTicket(Payout.valueOf(amount), ManualCount.valueOf("0"));

        assertThat(lottoTicket.count()).isEqualTo(expected);
    }

    @Test
    @DisplayName("당첨 등수 통계 확인")
    void calculateRanks() {
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
            LottoNumbers.valueOf("1,2,3,4,5,6"),
            LottoNumbers.valueOf("1,2,3,4,5,7"),
            LottoNumbers.valueOf("1,2,3,4,5,34"),
            LottoNumbers.valueOf("1,2,3,4,11,12"),
            LottoNumbers.valueOf("1,2,3,11,12,13"),
            LottoNumbers.valueOf("11,12,13,14,15,16")
        ));
        WinningNumbers winningNumbers =
            new WinningNumbers(LottoNumbers.valueOf("1,2,3,4,5,6"), LottoNumber.valueOf("7"));
        Map<Rank, Long> expected = new HashMap<>();
        Arrays.stream(Rank.values()).forEach(rank -> expected.put(rank, 1L));

        assertThat(lottoTicket.calculateRanks(winningNumbers).unwrap()).isEqualTo(expected);
    }
}