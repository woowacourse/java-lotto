package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import util.LottoNumberGenerator;
import util.ShuffleNumberGenerator;

public class LottoMachineTest {

    @Test
    public void createLottoMachine() {
        int money = 14000;
        LottoMachine lottoMachine = new LottoMachine(money, Collections.emptyList(), new ShuffleNumberGenerator());
        assertThat(lottoMachine).isInstanceOf(LottoMachine.class);
    }

    @Test
    public void calculateNoWinningProfit() {
        int money = 14000;
        LottoMachine lottoMachine = new LottoMachine(money, Collections.emptyList(), new ShuffleNumberGenerator());
        double profit = lottoMachine.calculateProfit();
        assertThat(profit).isEqualTo(0);
    }

    @Test
    public void calculateFifthWinningProfit() {
        int money = 1000;
        List<LottoNumber> winnerLottoNumbers = toLottoNumbers(List.of(1, 2, 3, 10, 11, 12));
        LottoNumber winnerBonusNumber = LottoNumber.of(45);
        LottoMachine lottoMachine = new LottoMachine(money, Collections.emptyList(),
                new AlwaysSameSixNumberGenerator());
        WinningLotto winningLotto = new WinningLotto(winnerLottoNumbers, winnerBonusNumber);
        lottoMachine.getResults(winningLotto);
        assertThat(lottoMachine.calculateProfit()).isEqualTo(5.00);
    }

    @Test
    public void getResultsTest() {
        int money = 1000;
        LottoMachine lottoMachine = new LottoMachine(money, Collections.emptyList(),
                new AlwaysSameSixNumberGenerator());
        List<LottoNumber> winningLottoNumbers = toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.of(7);
        WinningLotto winningLotto = new WinningLotto(winningLottoNumbers, bonusNumber);
        LottoResult lottoResult = lottoMachine.getResults(winningLotto);
        assertThat(lottoResult.getResultCount().get(LottoRank.RANK_1)).isEqualTo(1);
    }

    private List<LottoNumber> toLottoNumbers(List<Integer> list) {
        return list.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toList());
    }

    class AlwaysSameSixNumberGenerator implements LottoNumberGenerator {

        @Override
        public List<LottoNumber> generate() {
            return Arrays.asList(1, 2, 3, 4, 5, 6).stream()
                    .map(LottoNumber::of)
                    .collect(Collectors.toList());
        }
    }

}
