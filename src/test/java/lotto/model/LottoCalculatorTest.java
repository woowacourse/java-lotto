package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottoCalculatorTest {

    private LottoCalculator lottoCalculator;

    @BeforeEach
    void init() {
        Lottos lottos = makeLottos();
        WinningLotto winningLotto = makeWinningLotto(new int[]{1, 2, 3, 4, 5, 6}, 7);
        lottoCalculator = new LottoCalculator(lottos, winningLotto);
        lottoCalculator.calculateResult();
    }

    private Lottos makeLottos() {
        List<Lotto> lottos = new ArrayList<>();
        Lotto lotto = makeLotto(new int[]{1, 2, 3, 4, 5, 6}); //1등
        Lotto lotto1 = makeLotto(new int[]{2, 3, 4, 5, 6, 7}); //2등
        Lotto lotto2 = makeLotto(new int[]{3, 4, 5, 6, 7, 8}); //4등
        lottos.add(lotto);
        lottos.add(lotto1);
        lottos.add(lotto2);
        return new Lottos(lottos);
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

    @Test
    void 등수_개수_테스트_1등() {
        assertThat(lottoCalculator.getEachRankCount(Rank.FIRST)).isEqualTo(1);
    }

    @Test
    void 등수_개수_테스트_2등() {
        assertThat(lottoCalculator.getEachRankCount(Rank.SECOND)).isEqualTo(1);
    }

    @Test
    void 등수_개수_테스트_4등() {
        assertThat(lottoCalculator.getEachRankCount(Rank.FOURTH)).isEqualTo(1);
    }

    @Test
    void 수익률_테스트() {
        assertThat(lottoCalculator.getRevenue()).isEqualTo((double) (2000000000 + 30000000 + 50000) / 3000);
    }
}
