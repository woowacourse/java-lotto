package lotto.viewer;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.LottoAnnouncement;
import lotto.exception.LottoAnnouncementException;

public class AnnouncementInputView {

    private static final String DELIMITER = ", ";
    private static final String INPUT_WINNERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요";
    private static final String INPUT_BONUS_MESSAGE = "보너스 볼을 입력해주세요";

    final Scanner scanner;

    public AnnouncementInputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public LottoAnnouncement inputAnnouncement() throws LottoAnnouncementException{
        LottoAnnouncement inputAnnouncement;
        List<Integer> winners = inputWinningNumbers();
        int bonusNumber = inputBonusNumber();
        inputAnnouncement = new LottoAnnouncement(winners, bonusNumber);
        return inputAnnouncement;
    }

    private List<Integer> inputWinningNumbers() {
        System.out.println(INPUT_WINNERS_MESSAGE);
        return parseToWinner();
    }

    private List<Integer> parseToWinner() {
        String rawWinningNumbers = scanner.nextLine();
        String[] splittedWinningNumbers = rawWinningNumbers.split(DELIMITER);
        return Arrays.stream(splittedWinningNumbers)
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    private int inputBonusNumber() {
        System.out.println(INPUT_BONUS_MESSAGE);
        int bonusNumber= scanner.nextInt();
        scanner.nextLine();
        return bonusNumber;
    }
}
