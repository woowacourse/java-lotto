package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lotto.model.generator.CustomLottoGenerator;
import lotto.model.generator.LottoGenerator;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

    private LottoMachine lottoMachine;

    @DisplayName("로또머신 생성 정상 테스트")
    @Test
    void LottoMachineTest() {
        Money money = new Money(3000);
        LottoGenerator lottoGenerator = new CustomLottoGenerator();
        lottoMachine = new LottoMachine(lottoGenerator, money);
        assertThat(lottoMachine).isInstanceOf(LottoMachine.class);
    }

    @BeforeEach
    void init() {
        Money money = new Money(3000);
        WinningLotto winningLotto = makeWinningLotto(new int[]{1, 2, 3, 4, 5, 6}, 7);
        LottoGenerator lottoGenerator = new CustomLottoGenerator();
        lottoMachine = new LottoMachine(lottoGenerator, money);
        lottoMachine.calculateResult(winningLotto);
    }

    private WinningLotto makeWinningLotto(int[] numbers, int bonusNumber) {
        return new WinningLotto(makeLottoNumbers(numbers), new LottoNumber(bonusNumber));
    }

    private LottoNumbers makeLottoNumbers(int[] numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int num : numbers) {
            lottoNumbers.add(new LottoNumber(num));
        }
        return new LottoNumbers(lottoNumbers);
    }

    @DisplayName("1등 개수 테스트")
    @Test
    void firstCalculateResultTest() {
        assertThat(lottoMachine.getEachRankCount(Rank.FIRST)).isEqualTo(1);
    }

    @DisplayName("2등 개수 테스트")
    @Test
    void secondCalculateResultTes() {
        assertThat(lottoMachine.getEachRankCount(Rank.SECOND)).isEqualTo(1);
    }

    @DisplayName("4등 개수 테스트")
    @Test
    void fourthCalculateResultTes() {
        assertThat(lottoMachine.getEachRankCount(Rank.FOURTH)).isEqualTo(1);
    }

    @DisplayName("수익률 테스트")
    @Test
    void getRevenueTest() {
        assertThat(lottoMachine.getRevenue()).isEqualTo((double) (2000000000 + 30000000 + 50000) / 3000);
    }
}
