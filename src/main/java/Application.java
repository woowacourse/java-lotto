import java.util.List;
import java.util.stream.Collectors;

import domain.LottoGame;
import domain.LottoNumber;
import domain.Lottos;
import domain.WinningLotto;
import domain.WinningStatistics;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        final int purchasedMoney = InputView.getPurchaseMoney();

        LottoGame lottoGame = new LottoGame(purchasedMoney);

        Lottos lottos = lottoGame.getLottos();
        OutputView.showPurchasedLottos(lottos);

        final List<Integer> winningLottoNumbers = InputView.getWinningLottoNumbers();
        final int bonusBall = InputView.getBonusBall();

        WinningLotto winningLotto = new WinningLotto(winningLottoNumbers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toList()));

        LottoNumber bonusNumber = new LottoNumber(bonusBall);

        WinningStatistics winningStatistics = lottoGame.calculateWinningStatistics(winningLotto, bonusNumber);
        OutputView.showWinningStatistics(winningStatistics.getWinningStatistics());
    }
}
