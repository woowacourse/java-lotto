package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
<<<<<<< HEAD
=======
import util.LottoNumberGenerator;
>>>>>>> 543f606... feat: 로또 당첨 결과 출력 기능 구현
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
        LottoMachine lottoMachine = new LottoMachine(money, new ShuffleNumberGenerator());
        double profit = lottoMachine.calculateProfit();
        assertThat(profit).isEqualTo(0);
    }

    @Test
    public void calculateFifthWinningProfit() {
        int money = 14000;
        LottoMachine lottoMachine = new LottoMachine(money, new ShuffleNumberGenerator());
        lottoMachine.putLotto(LottoRank.RANK_5);
        assertThat(lottoMachine.calculateProfit()).isEqualTo(0.35);
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
            return Arrays.asList(1,2,3,4,5,6);
        }
    }

}
