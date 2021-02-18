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
    private static final String LOTTO_ANNOUNCE_SIZE_MESSAGE = "로또 번호의 갯수가 기준과 다릅니다.";
    private static final String WRONG_INTEGER_MESSAGE = "잘못된 정수 입력입니다.";
    public static final int LOTTE_POSSESSION_NUMBER = 6;

    final Scanner scanner;

    public AnnouncementInputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public LottoAnnouncement inputAnnouncement() {
        LottoAnnouncement inputAnnouncement;
        try {
            List<Integer> winners = inputWinningNumbers();
            int bonusNumber = inputBonusNumber();
            inputAnnouncement = new LottoAnnouncement(winners, bonusNumber);
        } catch (LottoAnnouncementException lottoAnnouncementException) {
            inputAnnouncement = inputAnnouncement();
        }
        return inputAnnouncement;
    }

    private List<Integer> inputWinningNumbers() {
        System.out.println(INPUT_WINNERS_MESSAGE);
        List<Integer> inputWinningNumbers;
        try {
            inputWinningNumbers = parseToWinner();
            checkSize(inputWinningNumbers);
        } catch (Exception e) {
            inputWinningNumbers = inputWinningNumbers();
        }
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

    private void checkSize(List<Integer> targetWinner) {
        if (targetWinner.size() != LOTTE_POSSESSION_NUMBER) {
            throw new LottoAnnouncementException(LOTTO_ANNOUNCE_SIZE_MESSAGE);
        }
    }

    private int inputBonusNumber() {
        System.out.println(INPUT_BONUS_MESSAGE);
        int bonusNumber;
        try {
            bonusNumber = scanner.nextInt();
        } catch (Exception e) {
            System.out.println(WRONG_INTEGER_MESSAGE);
            bonusNumber = inputBonusNumber();
        }
        scanner.nextLine();
        return bonusNumber;
    }
}
