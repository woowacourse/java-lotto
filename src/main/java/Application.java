import domain.AutoLottoGenerator;
import domain.Lotto;
import domain.LottoGame;
import domain.ManualLottoGenerator;
import domain.Money;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Application {

    private static final String MANUAL_NUMBER_TOO_LARGE_EXCEPTION = "[ERROR] 수동 로또 개수가 너무 많습니다.";

    private static Lotto createManualLotto() {
        List<Integer> lottoNumbers = InputView.askLottoNumbers();
        return Lotto.generateLotto(new ManualLottoGenerator(lottoNumbers));
    }

    private static Lotto createAutoLotto() {
        return Lotto.generateLotto(new AutoLottoGenerator());
    }

    private static void clickManualLottoGenerate(LottoGame lottoGame, int clickNum) {
        for (int i = 0; i < clickNum; ++i) {
            lottoGame.add(createManualLotto());
        }
    }

    private static void clickAutoLottoGenerate(LottoGame lottoGame, int clickNum) {
        for (int i = 0; i < clickNum; ++i) {
            lottoGame.add(createAutoLotto());
        }
    }

    public static void main(String[] args) {
        Money money = new Money(InputView.askMoneyInput());
        int totalNumber = money.money() / LottoGame.LOTTO_PRICE;
        LottoGame lottoGame = LottoGame.startLottoGame();
        int manualNumber = InputView.askNumberOfManualLotto();
        checkValidManualNumber(totalNumber, manualNumber);
        int autoNumber = totalNumber - manualNumber;
        InputView.askManualLottoNumbers();
        clickManualLottoGenerate(lottoGame, manualNumber);
        clickAutoLottoGenerate(lottoGame, autoNumber);
        OutputView.printLottosInformations(lottoGame.getLottos(), manualNumber, autoNumber);

        lottoGame.enterWinningLottoNumbersAndBonusNumber(InputView.askWinningNumbers(), InputView.askBonusNumber());
        OutputView.printWinningStatistic(lottoGame.produceResults());
        OutputView.printYield(lottoGame.calculateYield());
    }

    private static void checkValidManualNumber(int totalNumber, int manualNumber) {
        if (manualNumber > totalNumber) {
            throw new IllegalArgumentException(MANUAL_NUMBER_TOO_LARGE_EXCEPTION);
        }
    }
}
