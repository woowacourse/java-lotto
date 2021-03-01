package lotto.viewer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.Money;
import lotto.domain.Number;
import lotto.domain.Piece;
import lotto.exception.LottoException;
import lotto.exception.PieceException;

public class InputView {

    private static final String MANUAL_LOTTO_INPUT_MESSAGE = "수동으로 구매할 번호를 입력해주세요.";
    private static final String MANUAL_PIECE_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_WINNERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요";
    private static final String INPUT_BONUS_MESSAGE = "보너스 볼을 입력해주세요";
    private static final String PURCHASE_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String NOT_INTEGER_INPUT = "정수 입력이 아닙니다.";
    public static final String NOT_VALID_LOTTO_NUMBERS = "올바르지 않는 로또 번호 입력입니다.";
    private static final String DELIMITER = ", ";

    final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Money purchaseMoney() {
        int rawMoney;
        try {
            rawMoney = receiveInteger(PURCHASE_MONEY_MESSAGE);
        } catch (LottoException lottoException) {
            System.out.println(lottoException.getMessage());
            rawMoney = receiveInteger(PURCHASE_MONEY_MESSAGE);
        }
        return new Money(rawMoney);
    }

    public Piece inputManualPieces(Money money) throws PieceException {
        int rawAutoPieces;
        try {
            rawAutoPieces = receiveInteger(MANUAL_PIECE_MESSAGE);
        } catch (LottoException lottoException) {
            System.out.println(lottoException.getMessage());
            rawAutoPieces = receiveInteger(MANUAL_PIECE_MESSAGE);
        }
        return new Piece(money, rawAutoPieces);
    }

    public List<Number> inputWinnerNumbers() {
        System.out.println(INPUT_WINNERS_MESSAGE);
        return getValidLottoNumbers();
    }

    public Number inputBonusNumber() {
        int rawNumber;
        try {
            rawNumber = receiveInteger(INPUT_BONUS_MESSAGE);
        } catch (LottoException lottoException) {
            System.out.println(lottoException.getMessage());
            rawNumber =  receiveInteger(INPUT_BONUS_MESSAGE);
        }
        return Number.from(rawNumber);
    }

    private int receiveInteger(String receiveTargetMessage) throws LottoException {
        System.out.println(receiveTargetMessage);
        String rawInteger;
        try {
            rawInteger = scanner.nextLine();
            return Integer.parseInt(rawInteger);
        } catch (Exception integerException) {
            throw new LottoException(NOT_INTEGER_INPUT);
        }
    }

    public List<List<Number>> receiveManualNumbers(Piece manualPiece) {
        System.out.println(MANUAL_LOTTO_INPUT_MESSAGE);
        List<List<Number>> inputWinningNumbers = new ArrayList<>();
        for (int i = 0; i < manualPiece.getPieceNumber(); i++) {
            List<Number> lottoNumbers = getValidLottoNumbers();
            inputWinningNumbers.add(lottoNumbers);
        }
        return inputWinningNumbers;
    }

    private List<Number> getValidLottoNumbers() {
        try {
            return receiveWinners();
        } catch (LottoException lottoException) {
            System.out.println(lottoException.getMessage());
            return receiveWinners();
        }
    }

    private List<Number> receiveWinners() throws LottoException {
        try {
            String rawWinningNumbers = scanner.nextLine();
            String[] splittedWinningNumbers = rawWinningNumbers.split(DELIMITER);
            return Arrays.stream(splittedWinningNumbers)
                .map(String::trim)
                .map(element -> Number.from(Integer.parseInt(element)))
                .collect(Collectors.toList());
        } catch (Exception exception) {
            throw new LottoException(NOT_VALID_LOTTO_NUMBERS);
        }
    }

}
