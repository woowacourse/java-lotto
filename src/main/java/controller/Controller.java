package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import model.LottoWinningNumbers;
import model.OwnedLotto;
import model.lotto.Lotto;
import model.lotto.LottoMachine;
import model.result.PrizeResult;
import model.result.WinningDiscriminator;
import service.parser.BonusNumberParser;
import service.parser.BudgetParser;
import service.parser.WinningNumberParser;
import view.InputView;
import view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;

    public Controller(InputView inputView, OutputView outputView, LottoMachine lottoMachine) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMachine = lottoMachine;
    }

    public void start() {
        int lottoCount = inputResponseForBudget();

        OwnedLotto ownedLotto = buyLotto(lottoCount);
        outputView.displayLottoNumbers(ownedLotto);

        List<Integer> winningNumbers = inputResponseForWinningNumber();
        int bonusNumber = inputResponseForBonusNumber(winningNumbers);

        PrizeResult prizeResult = WinningDiscriminator.judge(ownedLotto,
                new LottoWinningNumbers(winningNumbers, bonusNumber));
        outputView.displayPrizeSummary(prizeResult);
    }

    private OwnedLotto buyLotto(int count) {
        ArrayList<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(lottoMachine.generateLotto());
        }

        return new OwnedLotto(lottos);
    }

    private int inputResponseForBudget() {
        return inputResponse(() -> BudgetParser.parseLottoCount(inputView.askForBudget()));
    }

    private List<Integer> inputResponseForWinningNumber() {
        return inputResponse(() -> WinningNumberParser.parseWinningNumbers(inputView.askForWinningNumber()));
    }

    private int inputResponseForBonusNumber(List<Integer> winningNumbers) {
        try {
            return BonusNumberParser.parseBonusNumber(winningNumbers, inputView.askForBonusNumber());
        } catch (IllegalArgumentException e) {
            outputView.displayErrorMessage(e.getMessage());
            System.exit(1);
            return 0;
        }
    }

    private <T> T inputResponse(Supplier<T> parser) {
        try {
            return parser.get();
        } catch (IllegalArgumentException e) {
            outputView.displayErrorMessage(e.getMessage());
            System.exit(1);
            return null;
        }
    }
}