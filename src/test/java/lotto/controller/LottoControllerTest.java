package lotto.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import java.util.ArrayList;
import java.util.List;
import lotto.model.Lotto;
import lotto.model.LottoCalculator;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.WinningLotto;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import lotto.view.ResultView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottoControllerTest {

    @BeforeEach
    void init() {
        List<Lotto> lottos = new ArrayList<>();
        Lotto lotto = makeLotto(new int[]{1, 2, 3, 4, 5, 6}); //1등
        Lotto lotto1 = makeLotto(new int[]{2, 3, 4, 5, 6, 7}); //2등
        Lotto lotto2 = makeLotto(new int[]{3, 4, 5, 6, 7, 8}); //4등
        lottos.add(lotto);
        lottos.add(lotto1);
        lottos.add(lotto2);
    }

    @Test
    void 실행_테스트() {
        assertThatCode(() -> run()).doesNotThrowAnyException();
    }

    public void run() {
        Money money = new Money(3000);
        Lottos lottos = makeLottos();
        ResultView.printBuyingLottosResult(lottos);
        WinningLotto winningLotto = makeWinningLotto(new int[]{1, 2, 3, 4, 5, 6}, 7);
        LottoCalculator lottoCalculator = new LottoCalculator(lottos, winningLotto);
        lottoCalculator.calculateResult();
        ResultView.printTotalRankResult(lottoCalculator);
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
}
