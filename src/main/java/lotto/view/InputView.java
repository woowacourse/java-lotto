package lotto.view;

import java.util.List;
import java.util.Scanner;
import lotto.view.message.Prompt;
import lotto.view.validator.InputValidator;

public class InputView {

    private final Scanner scanner;
    private final InputValidator inputValidator;

    public InputView(Scanner scanner, InputValidator inputValidator) {
        this.scanner = scanner;
        this.inputValidator = inputValidator;
    }

    public int readPurchaseAmount() {
        printPrompt(Prompt.PURCHASE.getContent());
        String content = readLine();
        printBlankLine();
        inputValidator.validateBlank(content);
        inputValidator.validateNumberFormat(content);
        return Integer.parseInt(content);
    }

    public List<Integer> readWinningNumbers() {
        printPrompt(Prompt.WINNING_NUMBERS.getContent());
        String content = readLine();
        printBlankLine();
        return parseWinningNumbers(content);
    }

    public int readBonusNumber() {
        printPrompt(Prompt.BONUS_NUMBER.getContent());
        String content = readLine();
        printBlankLine();
        inputValidator.validateNumberFormat(content);
        return Integer.parseInt(content);
    }

    private String readLine() {
        return scanner.nextLine();
    }

    private void printPrompt(String prompt) {
        System.out.println(prompt);
    }

    private void printBlankLine() {
        System.out.println();
    }

    private List<Integer> parseWinningNumbers(String content) {
        String spacelessContent = content.trim().replace(" ", "");
        List<String> splitContent = List.of(spacelessContent.split(","));
        inputValidator.validateNumberFormat(splitContent);
        return splitContent.stream().map(Integer::parseInt).toList();
    }
}
