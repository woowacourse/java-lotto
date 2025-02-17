package view;

import domain.BonusNumber;
import domain.PurchaseAmount;
import domain.WinningNumber;

import java.util.List;
import java.util.Scanner;
import utils.StringParser;

public class InputView {
    private static final String DELIMITER_COMMA = ",";

    public PurchaseAmount readPurchaseAmount() {
        Scanner scanner = new Scanner(System.in);
        final int number = StringParser.parseToNumber(scanner.next(), PurchaseAmount.INVALID_PURCHASEAMOUNT_FORMAT);
        return new PurchaseAmount(number);
    }

    public BonusNumber readBonusNumber() {
        Scanner scanner = new Scanner(System.in);
        final int number = StringParser.parseToNumber(scanner.next(), BonusNumber.INVALID_BONUS_NUMBER_FORMAT);
        return new BonusNumber(number);
    }

    public List<Integer> readWinningNumbers() {
        Scanner scanner = new Scanner(System.in);
        return StringParser.parseToNumbers(scanner.nextLine(), DELIMITER_COMMA, WinningNumber.INVALID_WINNING_NUMBERS_FORMAT);
    }
}
