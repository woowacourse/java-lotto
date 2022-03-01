import java.util.List;
import java.util.stream.Collectors;

import domain.Lotto;
import domain.LottoFactory;
import domain.LottoGame;
import domain.LottoNumber;
import domain.LottoGameMoney;
import domain.WinningLotto;
import domain.WinningStatistics;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        final LottoGameMoney purchasedMoney = new LottoGameMoney(InputView.getPurchaseAmount());
        final LottoGame lottoGame = new LottoGame(purchasedMoney);
        OutputView.showPurchasedLottos(lottoGame.getLottos());

        final WinningLotto winningLotto = createWinningLotto();
        final WinningStatistics winningStatistics = lottoGame.calculateWinningStatistics(winningLotto);
        OutputView.showWinningStatistics(winningStatistics.getWinningStatistics());
        OutputView.showProfitRate(winningStatistics.calculateProfitRate());
    }

    private static WinningLotto createWinningLotto() {
        final List<Integer> winningNumbers = InputView.getWinningLottoNumbers();
        Lotto winningLotto = LottoFactory.createLotto(winningNumbers);

        final int bonusNumber = InputView.getBonusBall();
        LottoNumber bonusLottoNumber = LottoNumber.valueOf(bonusNumber);

        return new WinningLotto(winningLotto, bonusLottoNumber);
    }
}
