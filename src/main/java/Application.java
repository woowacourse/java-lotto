import java.util.List;
import java.util.stream.Collectors;

import domain.LottoGame;
import domain.LottoNumber;
import domain.Money;
import domain.WinningLotto;
import domain.WinningStatistics;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        final Money purchasedMoney = new Money(InputView.getPurchaseMoney());
        final LottoGame lottoGame = new LottoGame(purchasedMoney);
        OutputView.showPurchasedLottos(lottoGame.getLottos());

        final WinningLotto winningLotto = createWinningLotto();
        final WinningStatistics winningStatistics = lottoGame.calculateWinningStatistics(winningLotto);
        OutputView.showWinningStatistics(winningStatistics.getWinningStatistics());
        OutputView.showProfitRate(winningStatistics.calculateProfitRate(purchasedMoney));
    }

    private static WinningLotto createWinningLotto() {
        final List<Integer> winningNumbers = InputView.getWinningLottoNumbers();
        List<LottoNumber> winningLottoNumbers = winningNumbers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toList());

        final int bonusNumber = InputView.getBonusBall();
        LottoNumber bonusLottoNumber = new LottoNumber(bonusNumber);

        return new WinningLotto(winningLottoNumbers, bonusLottoNumber);
    }
}
