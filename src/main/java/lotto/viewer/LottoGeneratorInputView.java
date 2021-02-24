package lotto.viewer;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.exception.LottoAnnouncementException;

public class LottoGeneratorInputView {

    private static final String DELIMITER = ", ";
    private static final String LOTTO_ANNOUNCE_SIZE_MESSAGE = "로또 번호의 갯수가 기준과 다릅니다.";

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
        checkSize(splittedWinningNumbers);
        return Arrays.stream(splittedWinningNumbers)
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    private void checkSize(String[] targetWinner) {
        if (targetWinner.length != Lotto.LOTTO_POSSESSION_NUMBER) {
            throw new LottoAnnouncementException(LOTTO_ANNOUNCE_SIZE_MESSAGE);
        }
    }
}
