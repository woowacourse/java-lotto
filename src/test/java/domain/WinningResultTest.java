package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultTest {
    private final Set<Integer> winningNumbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    private final WinningTicket winningTicket = WinningTicket.from(winningNumbers, 7);
    private final GenerateStrategy generateStrategy = () -> new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    private final LottoTickets lottoTickets = LottoTickets.from(new ArrayList<>(), new Money(14000), generateStrategy);
    private final WinningPrizeStrategy winningPrizeStrategy = new LottoWinningPrizeStrategy();

    @Test
    @DisplayName("당첨 결과 카운팅이 정상적으로 되는지 확인한다.")
    void winningCountResultTest() {
        WinningResult winningResult = WinningResult.from(lottoTickets, winningTicket, winningPrizeStrategy);
        assertThat(winningResult.getCountOfWinning().get(WinningPrize.FIRST)).isEqualTo(14);
    }
}