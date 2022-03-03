package lotto.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.model.Lotto;
import lotto.model.LottoCount;
import lotto.model.LottoMachine;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.WinningLotto;
import lotto.model.generator.CustomLottoGenerator;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import lotto.view.ResultView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoControllerTest {

    @DisplayName("실행 테스트")
    @Test
    void runLottoControllerTest() {
        assertThatCode(() -> run()).doesNotThrowAnyException();
    }

    public void run() {
        Money money = new Money(3000);
        LottoCount lottoCount = new LottoCount(2, money);
        Lottos manualLottos = makeLottos();
        LottoMachine lottoMachine = new LottoMachine(new CustomLottoGenerator(), money, lottoCount, manualLottos);
        ResultView.printBuyingLottosResult(lottoCount, lottoMachine.getLottos());
        WinningLotto winningLotto = makeWinningLotto(new int[]{1, 2, 3, 4, 5, 6}, 7);
        lottoMachine.calculateResult(winningLotto);
        ResultView.printTotalRankResult(lottoMachine);
    }

    private Lottos makeLottos() {
        Lotto first = makeLotto(new int[]{2, 3, 4, 5, 6, 7}); //2등
        Lotto second = makeLotto(new int[]{3, 4, 5, 6, 7, 8}); //4등
        return new Lottos(new ArrayList<>(Arrays.asList(first, second)));
    }

    private Lotto makeLotto(int[] numbers) {
        return new Lotto(makeLottoNumbers(numbers));
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
