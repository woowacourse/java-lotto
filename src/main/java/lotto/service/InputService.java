package lotto.service;

import lotto.domain.Lotto;
import lotto.vaildator.InputValidator;
import lotto.vaildator.LogicValidator;
import lotto.view.InputView;

import java.util.Arrays;
import java.util.List;

public class InputService {

    private final InputView inputView;

    public InputService(InputView inputView) {
        this.inputView = inputView;
    }

    public int readPurchaseAmount() {
        String content = inputView.readLine();
        InputValidator.validateBlank(content);
        InputValidator.validateNumberFormat(content);
        int purchaseAmount = Integer.parseInt(content);
        InputValidator.validatePurchaseAmount(purchaseAmount);
        return purchaseAmount;
    }

    public Lotto readWinningNumbers() {
        String content = inputView.readLine();
        List<Integer> winningNumbers = parseWinningNumbers(content);
        return new Lotto(winningNumbers);
    }

    public int readBonusNumber(Lotto winningLotto) {
        String content = inputView.readLine();
        InputValidator.validateNumberFormat(content);
        int bonusNumber = Integer.parseInt(content);
        LogicValidator.validateRange(bonusNumber, Lotto.MIN_LOTTO_NUMBER, Lotto.MAX_LOTTO_NUMBER);
        InputValidator.validateBonusNumber(winningLotto, bonusNumber);
        return bonusNumber;
    }

    private List<Integer> parseWinningNumbers(String content) {
        String spacelessContent = content.trim().replace(" ", "");
        List<String> splitContent = Arrays.stream(spacelessContent.split(",")).toList();
        InputValidator.validateWinningNumbers(splitContent);
        return splitContent.stream().map(Integer::parseInt).toList();
    }
}
