import domain.Lotto;
import domain.LottoFactory;
import domain.LottoNumber;
import domain.LottoMoney;
import domain.LottoPurchaseCount;
import domain.Lottos;
import domain.WinningLotto;
import domain.WinningStatistics;
import view.InputView;
import view.OutputView;

public class Application {

    private static final LottoFactory lottoFactory = new LottoFactory();

    public static void main(String[] args) {
        final LottoMoney lottoMoney = createLottoMoney();
        final LottoPurchaseCount lottoPurchaseCount = createLottoPurchaseCount(lottoMoney);

        final Lottos lottos = createLottos(lottoPurchaseCount);
        OutputView.showPurchasedLottos(lottoPurchaseCount, lottos.getLottos());

        final WinningLotto winningLotto = createWinningLotto();
        final WinningStatistics winningStatistics = createWinningStatistics(lottos, winningLotto);
        OutputView.showWinningStatistics(winningStatistics.getWinningStatistics());
        OutputView.showProfitRate(winningStatistics.calculateProfitRate());
    }

    private static LottoMoney createLottoMoney() {
        try {
            return new LottoMoney(InputView.getPurchaseMoney());
        } catch (Exception e) {
            OutputView.showErrorMessage(e);
            return createLottoMoney();
        }
    }

    private static LottoPurchaseCount createLottoPurchaseCount(LottoMoney lottoMoney) {
        try {
            return lottoMoney.calculateLottoCountRefactor(InputView.getPurchaseManualCount());
        } catch (Exception e) {
            OutputView.showErrorMessage(e);
            return createLottoPurchaseCount(lottoMoney);
        }
    }

    private static Lottos createLottos(LottoPurchaseCount lottoPurchaseCount) {
        try {
            return lottoFactory.generateLottos(
                InputView.getManualLottoNumbers(lottoPurchaseCount.getManualCount()),
                lottoPurchaseCount);
        } catch (Exception e) {
            OutputView.showErrorMessage(e);
            return createLottos(lottoPurchaseCount);
        }
    }

    private static WinningLotto createWinningLotto() {
        return new WinningLotto(createWinningLottoBall(), createBonusBall());
    }

    private static Lotto createWinningLottoBall() {
        try {
            return lottoFactory.generateLotto(InputView.getWinningLottoNumbers());
        } catch (Exception e) {
            OutputView.showErrorMessage(e);
            return createWinningLottoBall();
        }
    }

    private static LottoNumber createBonusBall() {
        try {
            return LottoNumber.valueOf(InputView.getBonusBall());
        } catch (Exception e) {
            OutputView.showErrorMessage(e);
            return createBonusBall();
        }
    }

    private static WinningStatistics createWinningStatistics(Lottos lottos, WinningLotto winningLotto) {
        return new WinningStatistics(lottos.calculateLottoReward(winningLotto));
    }
}
