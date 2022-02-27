package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.LottoNumberGenerator;
import util.ShuffleNumberGenerator;

public class LottoMachineTest {

    @Test
    public void createLottoMachine() {
        int money = 14000;
        LottoMachine lottoMachine = new LottoMachine(money, new ShuffleNumberGenerator());
        assertThat(lottoMachine).isInstanceOf(LottoMachine.class);
    }

    @Test
    public void calculateNoWinningProfit() {
        int money = 14000;
        LottoMachine lottoMachine = new LottoMachine(money, new AlwaysSameLastSixNumberGenerator());
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        double profit = lottoMachine.calculateProfit(new LottoResult(lottoMachine.getLottoTicket(), winningLotto));
        assertThat(profit).isEqualTo(0);
    }

    @Test
    public void calculateFifthWinningProfit() {
        int money = 14000;
        LottoMachine lottoMachine = new LottoMachine(money, new AlwaysSameSixNumberGenerator());
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 7, 8, 9), 10);
        assertThat(
                lottoMachine.calculateProfit(
                        new LottoResult(lottoMachine.getLottoTicket(), winningLotto))
        ).isEqualTo(5);
    }

    @Test
    public void getResultsTest() {
        int money = 1000;
        LottoMachine lottoMachine = new LottoMachine(money, new AlwaysSameSixNumberGenerator());
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        LottoResult lottoResult = lottoMachine.getResults(winningLotto);
        assertThat(lottoResult.getResultCount().get(LottoRank.RANK_1)).isEqualTo(1);
    }

    class AlwaysSameSixNumberGenerator implements LottoNumberGenerator {

        @Override
        public List<Integer> generate() {
            return Arrays.asList(1, 2, 3, 4, 5, 6);
        }
    }

    class AlwaysSameLastSixNumberGenerator implements LottoNumberGenerator {
        @Override
        public List<Integer> generate() {
            return Arrays.asList(40, 41, 42, 43, 44, 45);
        }
    }

}
