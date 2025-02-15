package model.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class LottoMachineTest {
    @DisplayName("로또생성기_테스트")
    @Test
    void 로또생성기_테스트() {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        LottoMachine lottoMachine = new LottoMachine(randomNumberGenerator);
        Lotto lotto = lottoMachine.generateLotto();

        assertThat(lotto.getNumbers()).isSorted();
    }
}