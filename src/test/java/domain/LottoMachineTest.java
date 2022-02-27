package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

    private LottoMachine lottoMachine;
    private WinningLotto winningLotto;

    @BeforeEach
    public void setUp() {
        lottoMachine = new LottoMachine(1000, new AlwaysSameSixNumberGenerator());
        winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
    }

    @Test
    public void createLottoMachine() {
        assertThat(lottoMachine).isInstanceOf(LottoMachine.class);
    }

    @Test
    public void calculateNoWinningProfit() {
        lottoMachine = new LottoMachine(1000, new AlwaysSameLastSixNumberGenerator());
        double profit = lottoMachine.calculateProfit(new LottoResult(lottoMachine.getLottoTicket(), winningLotto));
        assertThat(profit).isEqualTo(0);
    }

    @Test
    public void calculateFifthWinningProfit() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 7, 8, 9), 10);
        assertThat(
                lottoMachine.calculateProfit(
                        new LottoResult(lottoMachine.getLottoTicket(), winningLotto))
        ).isEqualTo(5);
    }

    @Test
    public void getResultsTest() {
        LottoResult lottoResult = lottoMachine.getResults(winningLotto);
        assertThat(lottoResult.getResultCount().get(LottoRank.RANK_1)).isEqualTo(1);
    }
}
