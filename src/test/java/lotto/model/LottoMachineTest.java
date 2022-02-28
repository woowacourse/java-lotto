package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

    private Lottos lottos;
    private WinningLotto winningLotto;
    private LottoMachine lottoMachine;

    @BeforeEach
    void init() {
        Lotto lotto1 = makeLotto(new int[]{1, 2, 3, 4, 5, 6}); //1등
        Lotto lotto2 = makeLotto(new int[]{3, 4, 5, 6, 7, 8}); //4등
        lottos = new Lottos(new Money(0));
        lottos.insert(lotto1);
        lottos.insert(lotto2);
        winningLotto = makeWinningLotto(new int[]{1, 2, 3, 4, 5, 6}, 7);
        lottoMachine = new LottoMachine(lottos, winningLotto);
        lottoMachine.calculateResult();
    }

    @Test
    void 등수_개수_테스트() {
        assertThat(lottoMachine.getEachRankCount(Rank.FIRST)).isEqualTo(1);
    }

    @Test
    void 수익률_테스트() {
        assertThat(lottoMachine.getRevenue()).isEqualTo((2000000000 + 50000) / 2000);
    }

    private Lotto makeLotto(int[] numbers) {
        return new Lotto((minimumNumber, maximumNumber, lottoLength) -> makeLottoNumbers(numbers));
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
}
