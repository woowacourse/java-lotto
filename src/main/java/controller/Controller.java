package controller;

import static common.constant.NumberConstants.LOTTO_PRICE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import model.LottoWinningNumbers;
import model.PurchasedLotto;
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
        int budget = inputResponseForBudget().orElse(0);
        int lottoCount = budget / LOTTO_PRICE;

        PurchasedLotto purchasedLotto = buyLotto(lottoCount);
        outputView.displayLottoNumbers(purchasedLotto);

        List<Integer> winningNumbers = inputResponseForWinningNumber().orElse(Collections.emptyList());
        int bonusNumber = inputResponseForBonusNumber(winningNumbers);

        PrizeResult prizeResult = WinningDiscriminator.judge(purchasedLotto,
                new LottoWinningNumbers(winningNumbers, bonusNumber));
        outputView.displayPrizeSummary(prizeResult);
    }

    private PurchasedLotto buyLotto(int count) {
        ArrayList<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(lottoMachine.generateLotto());
        }

        return new PurchasedLotto(lottos);
    }

    private Optional<Integer> inputResponseForBudget() {
        return inputResponse(() -> BudgetParser.parseBudget(inputView.askForBudget()));
    }

    private Optional<List<Integer>> inputResponseForWinningNumber() {
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

    private <T> Optional<T> inputResponse(Supplier<T> parser) {
        try {
            return Optional.ofNullable(parser.get());
        } catch (IllegalArgumentException e) {
            outputView.displayErrorMessage(e.getMessage());
            System.exit(1);
            return Optional.empty();
        }
    }
}