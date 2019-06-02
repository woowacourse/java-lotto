import domain.LottoSimulationResult;
import domain.LottoSimulator;
import domain.Money;
import domain.WinningLotto;
import exception.BonusBallDuplicationException;
import exception.LottoException;
import util.MoneyParser;
import util.WinningLottoParser;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Main {
    private static final String EMPTY = "";

    public static void main(String[] args) {
        Money money = readMoney(EMPTY);
        WinningLotto winningLotto = readWinningLotto(EMPTY);

        LottoSimulator simulator = new LottoSimulator(winningLotto);

        LottoSimulationResult result = simulator.play(money);

        OutputView.printLottoGroup(result.lottoGroup);
        OutputView.printRankAnalysis(result.rankAnalysis);
    }

    private static Money readMoney(String notifyingMessage) {
        String input = InputView.readMoney(notifyingMessage);
        try {
            return MoneyParser.parse(input);
        } catch (IllegalArgumentException e) {
            return readMoney(notifyingMessage);
        }
    }

    private static WinningLotto readWinningLotto(String notifyingMessage) {
        List<String> inputs = InputView.readWinningLotto(notifyingMessage);
        try {
            return WinningLottoParser.parse(inputs.get(0), inputs.get(1));
        } catch (BonusBallDuplicationException e) {
            return readWinningLotto(e.getMessage());
        } catch (LottoException e) {
            return readWinningLotto(e.getMessage());
        } catch (IllegalArgumentException e) {
            return readWinningLotto(e.getMessage());
        }
    }
}
