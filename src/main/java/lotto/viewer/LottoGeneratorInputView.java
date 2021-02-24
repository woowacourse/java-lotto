package lotto.viewer;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.Money;
import lotto.domain.Piece;
import lotto.exception.MoneyException;
import lotto.exception.PieceException;

public class LottoGeneratorInputView {

    private static final String DELIMITER = ", ";

    final Scanner scanner;

    public LottoGeneratorInputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Piece inputManualPieces(Money money) throws PieceException {
        int inputAutoPieces = scanner.nextInt();
        scanner.nextLine();
        return new Piece(money, inputAutoPieces);
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

    private void checkExcessManualPieces(Money money, int inputAutoPieces) {
        if (money.getLottoPieces() < inputAutoPieces) {
            throw new MoneyException(Money.EXCESS_LOTTO_PIECES);
        }
    }
}
