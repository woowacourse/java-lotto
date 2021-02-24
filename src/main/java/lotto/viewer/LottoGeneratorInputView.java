package lotto.viewer;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LottoGeneratorInputView {

    private static final String DELIMITER = ", ";

    final Scanner scanner;

    public LottoGeneratorInputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<Integer> inputWinningNumbers() {
        List<Integer> inputWinningNumbers;
        inputWinningNumbers = parseToWinner();
        return inputWinningNumbers;
    }

    private List<Integer> parseToWinner() {
        String rawWinningNumbers = scanner.nextLine();
        String[] splittedWinningNumbers = rawWinningNumbers.split(DELIMITER);
        return Arrays.stream(splittedWinningNumbers)
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }
}
