import domain.lotto.LottoFactory;
import domain.lotto.LottoTickets;
import domain.lotto.WinningLotto;
import domain.money.LottoMoney;
import domain.result.LottoResult;
import parser.GameParser;
import view.input.InputView;
import view.output.OutputView;

public class Application {

    private static GameParser gameParser = new GameParser();

    public static void main(String[] args) {
        LottoMoney lottoMoney = new LottoMoney(gameParser.parseInputToInt(InputView.inputLottoMoney()));
        LottoTickets lottoTickets = LottoFactory.publishLottoTickets(lottoMoney);
        OutputView.printLottoTickets(lottoTickets);
        WinningLotto winningLotto = gameParser.createWinningLotto(InputView.inputWinningLotto(),
                InputView.inputBonusNumber());
        LottoResult lottoResult = lottoTickets.getLottoResults(winningLotto);
        OutputView.printLottoResult(lottoResult);
        OutputView.printProfit(lottoResult, lottoMoney);
    }
}
