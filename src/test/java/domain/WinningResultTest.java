package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultTest {

    @Test
    @DisplayName("1000원으로 1등 당첨 시, 수익률 확인")
    void checkRateOfProfitFirstWinning() {
        Lotto myLotto = new Lotto(Stream.of(1, 5, 9, 11, 16, 35)
                .map(Number::new)
                .collect(Collectors.toList()));
        Lotto winningLotto = new Lotto(Stream.of(1, 5, 9, 11, 16, 35)
                .map(Number::new)
                .collect(Collectors.toList()));
        Number bonusNumber = new Number(39);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
        LottoTicket lottoTicket = new LottoTicket(List.of(myLotto));
        Money money = new Money(1000);

        // when
        WinningResult winningResult = new WinningResult(lottoTicket.checkLottoTicketWinningCountByRank(winningNumbers));

        //then
        assertThat(winningResult.getRateOfProfit(money)).isEqualTo(2000000.0);
    }
}
