import domain.LottoGame;
import domain.Lottos;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        final int purchasedMoney = InputView.getPurchaseMoney();

        LottoGame lottoGame = new LottoGame(purchasedMoney);

        Lottos lottos = lottoGame.getLottos();
        OutputView.showPurchasedLottos(lottos);
    }
}
