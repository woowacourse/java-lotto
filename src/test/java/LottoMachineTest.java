import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

    @Test
    @DisplayName("1등 결과 반환")
    void firstPrize() {
        LottoNumbers winningNumbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);
        LottoNumbers lottoNumbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        LottoMachine lottoMachine = new LottoMachine(winningNumbers, bonusNumber);
        LottoResult result = lottoMachine.getResult(lottoNumbers);
        assertThat(result.getFirstPrizeCount()).isEqualTo(1);
    }

}
