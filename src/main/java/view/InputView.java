package view;

import static view.messages.InputExceptionMessages.*;
import static view.messages.InputViewMessages.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import domain.NumOfLottery;

public class InputView {

	private static final Scanner scanner = new Scanner(System.in);
	private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");
	private static final Pattern WINNING_NUMBER_PATTERN = Pattern.compile("^([1-9]{1,2}[,][\\s]?){5}[1-9]{1,2}$");
	private static final String WINNING_NUMBER_DELIMITER = ",";

	public int inputValidMoney() {
		final String money = inputMoney();
		return Integer.parseInt(money);
	}

	private String inputMoney() {
		System.out.println(INPUT_MONEY_MESSAGE.getMessage());
		final String money = scanner.nextLine();
		try {
			validateMoney(money);
			return money;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputMoney();
		}
	}

	private void validateMoney(final String money) {
		validateNumber(money);
	}

	private void validateNumber(String number) {
		if (!NUMBER_PATTERN.matcher(number).matches()) {
			throw new IllegalArgumentException(INVALID_INPUT_NUMBER_EXCEPTION.getMessage());
		}
	}

	public List<Integer> inputValidLotteryNumber() {
		System.out.println(INPUT_WINNING_NUMBER_MESSAGE.getMessage());
		try {
			final String numbers = inputLotteryNumber();
			return splitNumbers(numbers);
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputValidLotteryNumber();
		}
	}

	private String inputLotteryNumber() {
		final String lotteryNumber = scanner.nextLine();
		validateLotteryNumber(lotteryNumber);
		return lotteryNumber;
	}

	public List<List<Integer>> inputManualLotteryNumber(final NumOfLottery numOfLottery) {
		System.out.println("수동으로 구매할 번호를 입력해 주세요.");
		List<List<Integer>> manualNumbers = new ArrayList<>();
		try {
			inputLotteryNumberToManualNumbers(numOfLottery, manualNumbers);
			return manualNumbers;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputManualLotteryNumber(numOfLottery);
		}
	}

	private void inputLotteryNumberToManualNumbers(final NumOfLottery numOfLottery, final List<List<Integer>> manualNumbers) {
		for (int i = 0; i < numOfLottery.getNumOfManualLottery(); i++) {
			final String numbers = inputLotteryNumber();
			manualNumbers.add(splitNumbers(numbers));
		}
	}

	private List<Integer> splitNumbers(String numbers) {
		return Arrays.stream(numbers.split(WINNING_NUMBER_DELIMITER))
			.map(String::trim)
			.map(Integer::parseInt)
			.collect(Collectors.toList());
	}

	private void validateLotteryNumber(String lotteryNumber) {
		if (!WINNING_NUMBER_PATTERN.matcher(lotteryNumber).matches()) {
			throw new IllegalArgumentException(INVALID_WINNING_NUMBER_EXCEPTION.getMessage());
		}
	}

	public int inputValidBonusNumber() {
		return Integer.parseInt(inputBonusNumber());
	}

	public int inputValidNumOfManualLottery() {
		return Integer.parseInt(inputNumOfManualLottery());
	}

	private String inputNumOfManualLottery() {
		System.out.println(INPUT_NUM_OF_MANUAL_LOTTERY_MESSAGE.getMessage());
		final String numOfManualLottery = scanner.nextLine();
		try {
			validateNumber(numOfManualLottery);
			return numOfManualLottery;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputNumOfManualLottery();
		}
	}

	private String inputBonusNumber() {
		System.out.println(INPUT_BONUS_BALL_MESSAGE.getMessage());
		final String bonusNumber = scanner.nextLine();
		try {
			validateNumber(bonusNumber);
			return bonusNumber;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputBonusNumber();
		}
	}
}
