import static org.assertj.core.api.Assertions.assertThat;

import model.lotto.Lotto;
import model.lotto.LottoMachine;
import model.lotto.RandomNumberGenerator;
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