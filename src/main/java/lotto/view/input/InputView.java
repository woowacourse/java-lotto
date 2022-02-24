package lotto.view.input;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import lotto.exception.LottoException;
import lotto.exception.LottoExceptionStatus;
import lotto.exception.ball.BallNumberExceptionStatus;
import lotto.exception.credit.CreditMoneyExceptionStatus;

public class InputView {

	private static final String MESSAGE_OF_REQUEST_CREDIT_MONEY = "구입금액을 입력해 주세요.";
	private static final String MESSAGE_OF_REQUEST_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
	private static final String MESSAGE_OF_REQUEST_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
	private static final String DELIMITER = ",";

	private static final Scanner scanner = new Scanner(System.in);

	private static int parseNumber(final String text, LottoExceptionStatus lottoExceptionStatus) {
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException ex) {
			throw new LottoException(lottoExceptionStatus);
		}
	}

	public static int requestCreditMoney() {
		System.out.println(MESSAGE_OF_REQUEST_CREDIT_MONEY);
		return parseCreditMoney(scanner.nextLine());
	}

	private static int parseCreditMoney(final String inputValue) {
		return parseNumber(inputValue, CreditMoneyExceptionStatus.MONEY_IS_NOT_NUMERIC);
	}

	public static List<Integer> requestWinningNumbers() {
		System.out.println(MESSAGE_OF_REQUEST_WINNING_NUMBERS);
		final String inputValue = scanner.nextLine();
		return Arrays.stream(inputValue.split(DELIMITER, -1))
			.map(String::trim)
			.map(InputView::parseWinningNumber)
			.collect(Collectors.toUnmodifiableList());
	}

	private static int parseWinningNumber(final String inputValue) {
		return parseNumber(inputValue, BallNumberExceptionStatus.BALL_IS_NOT_NUMERIC);
	}

	public static int requestBonusNumber() {
		System.out.println(MESSAGE_OF_REQUEST_BONUS_NUMBER);
		return parseBonusNumber(scanner.nextLine());
	}

	private static int parseBonusNumber(final String inputValue) {
		return parseNumber(inputValue, BallNumberExceptionStatus.BALL_IS_NOT_NUMERIC);
	}

}
