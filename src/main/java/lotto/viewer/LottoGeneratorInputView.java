package lotto.viewer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.Piece;
import lotto.domain.generator.LottoManualGenerator;

public class LottoGeneratorInputView {

    private static final String MANUAL_LOTTO_INPUT_MESSAGE = "수동으로 구매할 번호를 입력해주세요.";
    private static final String DELIMITER = ", ";

    final Scanner scanner;

    public LottoGeneratorInputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public LottoManualGenerator lottoManualGenerator(Piece manualPiece) {
        System.out.println(MANUAL_LOTTO_INPUT_MESSAGE);
        List<List<Integer>> inputWinningNumbers = new ArrayList<>();
        for (int i = 0; i < manualPiece.getPiece(); i++) {
            inputWinningNumbers.add(parseToWinner());
        }
        return new LottoManualGenerator(inputWinningNumbers);
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
