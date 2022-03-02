package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.strategy.NumberGenerateStrategy;
import domain.strategy.DefaultWinningPrizeStrategy;
import domain.strategy.WinningPrizeStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultTest {
    private final Set<Integer> winningNumbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    private final WinningTicket winningTicket = WinningTicket.of(winningNumbers, 7);
    private final NumberGenerateStrategy numberGenerateStrategy = () -> new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    private final LottoTickets lottoTickets = LottoTickets.of(new ArrayList<>(), new Money(14000),
            numberGenerateStrategy);
    private final WinningPrizeStrategy winningPrizeStrategy = new DefaultWinningPrizeStrategy();

    @Test
    @DisplayName("당첨 결과 카운팅이 정상적으로 되는지 확인한다.")
    void winningCountResultTest() {
        WinningResult winningResult = WinningResult.of(lottoTickets, winningTicket, winningPrizeStrategy);
        assertThat(winningResult.getCountOfWinning().get(WinningPrize.FIRST)).isEqualTo(14);
    }
}