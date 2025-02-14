package lotto.service;

import java.util.Arrays;
import java.util.List;
import lotto.constant.Prompt;
import lotto.domain.Lotto;
import lotto.vaildator.CommonValidator;
import lotto.vaildator.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class InputService {

    private final InputView inputView;
    private final OutputView outputView;

    public InputService(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public int readPurchaseAmount() {
        printPrompt(Prompt.PURCHASE.getContent());
        String content = inputView.readLine();
        outputView.printBlankLine();
        InputValidator.validateBlank(content);
        InputValidator.validateNumberFormat(content);
        int purchaseAmount = Integer.parseInt(content);
        InputValidator.validatePurchaseAmount(purchaseAmount);
        return purchaseAmount;
    }

    public Lotto readWinningNumbers() {
        printPrompt(Prompt.WINNING_NUMBERS.getContent());
        String content = inputView.readLine();
        outputView.printBlankLine();
        List<Integer> winningNumbers = parseWinningNumbers(content);
        return new Lotto(winningNumbers);
    }

    public int readBonusNumber(Lotto winningLotto) {
        printPrompt(Prompt.BONUS_NUMBER.getContent());
        String content = inputView.readLine();
        outputView.printBlankLine();
        InputValidator.validateNumberFormat(content);
        int bonusNumber = Integer.parseInt(content);
        CommonValidator.validateRange(bonusNumber, Lotto.MIN_LOTTO_NUMBER, Lotto.MAX_LOTTO_NUMBER);
        InputValidator.validateBonusNumber(winningLotto, bonusNumber);
        return bonusNumber;
    }

    private List<Integer> parseWinningNumbers(String content) {
        String spacelessContent = content.trim().replace(" ", "");
        List<String> splitContent = Arrays.stream(spacelessContent.split(",")).toList();
        InputValidator.validateWinningNumbers(splitContent);
        return splitContent.stream().map(Integer::parseInt).toList();
    }

    private void printPrompt(String prompt) {
        outputView.printLine(prompt);
    }
}
