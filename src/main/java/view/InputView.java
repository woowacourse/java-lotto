package view;

import domain.BonusNumber;
import domain.Money;
import exception.ErrorMessage;
import java.util.List;
import java.util.Scanner;
import utils.StringParser;

public class InputView {
    private static final String DELIMITER_COMMA = ",";

    public Money readMoney() {
        Scanner scanner = new Scanner(System.in);
        final int number = StringParser.parseToNumber(scanner.next(), ErrorMessage.INVALID_MONEY_FORMAT);
        return new Money(number);
    }

    public BonusNumber readBonusNumber() {
        Scanner scanner = new Scanner(System.in);
        final int number = StringParser.parseToNumber(scanner.next(), ErrorMessage.INVALID_BONUS_NUMBER_FORMAT);
        return new BonusNumber(number);
    }

    public List<Integer> readWinningNumbers() {
        Scanner scanner = new Scanner(System.in);
        return StringParser.parseToNumbers(scanner.nextLine(), DELIMITER_COMMA, ErrorMessage.INVALID_WINNING_NUMBERS_FORMAT);
    }
}
