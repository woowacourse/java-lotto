package lotto.viewer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.LottoAnnouncement;
import lotto.domain.Money;
import lotto.domain.Piece;
import lotto.domain.generator.LottoManualGenerator;
import lotto.exception.LottoAnnouncementException;
import lotto.exception.PieceException;

public class InputView {

    private static final String MANUAL_LOTTO_INPUT_MESSAGE = "수동으로 구매할 번호를 입력해주세요.";
    private static final String MANUAL_PIECE_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_WINNERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요";
    private static final String INPUT_BONUS_MESSAGE = "보너스 볼을 입력해주세요";
    private static final String PURCAHSE_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String DELIMITER = ", ";

    final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public LottoAnnouncement inputAnnouncement() throws LottoAnnouncementException {
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

    private int inputBonusNumber() {
        System.out.println(INPUT_BONUS_MESSAGE);
        int bonusNumber= scanner.nextInt();
        scanner.nextLine();
        return bonusNumber;
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

    public Money purchaseMoney() {
        System.out.println(PURCAHSE_MONEY_MESSAGE);
        int rawMoney = scanner.nextInt();
        Money money = new Money(rawMoney);
        scanner.nextLine();
        return money;
    }

    public Piece inputManualPieces(Money money) throws PieceException {
        System.out.println(MANUAL_PIECE_MESSAGE);
        int inputAutoPieces = scanner.nextInt();
        scanner.nextLine();
        return new Piece(money, inputAutoPieces);
    }
}
