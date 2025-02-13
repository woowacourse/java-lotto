package controller;

import java.util.ArrayList;
import java.util.List;
import model.LottoWinningNumbers;
import model.OwnedLotto;
import model.PrizeResult;
import model.lotto.Lotto;
import service.Judgement;
import service.LottoMaker;
import service.parser.BonusNumberParser;
import service.parser.BudgetParser;
import service.parser.WinningNumberParser;
import view.InputView;
import view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMaker lottoMaker;

    public Controller(InputView inputView, OutputView outputView, LottoMaker lottoMaker) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMaker = lottoMaker;
    }

    public void start() {
        int lottoCount = inputResponseForBudget();

        OwnedLotto ownedLotto = buyLotto(lottoCount);
        outputView.displayLottoNumbers(ownedLotto);

        List<Integer> winningNumbers = inputResponseForWinningNumber();
        int bonusNumber = inputResponseForBonusNumber(winningNumbers);

        PrizeResult prizeResult = Judgement.judge(ownedLotto, new LottoWinningNumbers(winningNumbers, bonusNumber));
        outputView.displayPrizeSummary(prizeResult);
    }

    private OwnedLotto buyLotto(int count) {
        ArrayList<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(lottoMaker.generateLotto());
        }

        return new OwnedLotto(lottos);
    }

    private int inputResponseForBudget() {
        try {
            return BudgetParser.parseLottoCount(inputView.askForBudget());
        } catch (IllegalArgumentException e) {
            outputView.displayErrorMessage(e.getMessage());
            throw e;
        }
    }

    private List<Integer> inputResponseForWinningNumber() {
        try {
            return WinningNumberParser.parseWinningNumbers(inputView.askForWinningNumber());
        } catch (IllegalArgumentException e) {
            outputView.displayErrorMessage(e.getMessage());
            throw e;
        }
    }

    private int inputResponseForBonusNumber(List<Integer> winningNumbers) {
        try {
            return BonusNumberParser.parseBonusNumber(winningNumbers, inputView.askForBonusNumber());
        } catch (IllegalArgumentException e) {
            outputView.displayErrorMessage(e.getMessage());
            throw e;
        }
    }
}