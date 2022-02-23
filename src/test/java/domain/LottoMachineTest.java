package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class LottoMachineTest {

    @Test
    public void createLottoMachine() {
        int money = 14000;
        LottoMachine lottoMachine = new LottoMachine(money);
        assertThat(lottoMachine).isInstanceOf(LottoMachine.class);
    }

    @Test
    public void calculateNoWinningProfit() {
        int money = 14000;
        LottoMachine lottoMachine = new LottoMachine(money);
        double profit = lottoMachine.calculateProfit();
        assertThat(profit).isEqualTo(0);
    }

    @Test
    public void calculateFifthWinningProfit() {
        int money = 14000;
        LottoMachine lottoMachine = new LottoMachine(money);
        lottoMachine.putLotto(LottoRank.RANK_5);
        assertThat(lottoMachine.calculateProfit()).isEqualTo(0.35);
    }
}
