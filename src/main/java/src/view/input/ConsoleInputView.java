package src.view.input;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleInputView implements InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String WINNING_LOTTO_DELIMITER = ", ";

    @Override
    public int inputPurchaseMoney() {
        try {
            return scanner.nextInt();
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("잘못된 입력입니다.", e);
        }
    }

    @Override
    public List<Integer> inputWinningLottoNumbers() {
        try {
            scanner.nextLine();
            String input = scanner.nextLine();
            List<String> parsedWinningLottoNumbers = parseWinningLottoNumbers(input);

            return parsedWinningLottoNumbers.stream().map(Integer::parseInt).toList();
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("잘못된 입력입니다.", e);
        }
    }

    private List<String> parseWinningLottoNumbers(final String input) {
        List<String> parsedWinningLottoNumbers = Arrays.stream(input.split(WINNING_LOTTO_DELIMITER)).toList();
        parsedWinningLottoNumbers.forEach(this::validateWinningLottoInput);

        return parsedWinningLottoNumbers;
    }

    private void validateWinningLottoInput(final String winningLottoNumber) {
        if (!winningLottoNumber.matches("[0-9]+")) {
            throw new IllegalArgumentException("구분자 형식에 맞지 않습니다.");
        }
    }
}
