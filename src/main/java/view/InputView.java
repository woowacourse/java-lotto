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
	private static final Pattern LOTTERY_NUMBER_PATTERN = Pattern.compile("^([1-9]{1,2}[,][\\s]?){5}[1-9]{1,2}$");
	private static final String LOTTERY_NUMBER_DELIMITER = ",";

	public int inputValidMoney() {
		System.out.println(INPUT_MONEY_MESSAGE.getMessage());
		try {
			final String money = inputMoney();
			return Integer.parseInt(money);
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputValidMoney();
		}
	}

	private String inputMoney() {
		final String money = scanner.nextLine();
		validateNumber(money);
		return money;
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

	private void inputLotteryNumberToManualNumbers(final NumOfLottery numOfLottery,
		final List<List<Integer>> manualNumbers) {
		for (int i = 0; i < numOfLottery.getNumOfManualLottery(); i++) {
			final String numbers = inputLotteryNumber();
			manualNumbers.add(splitNumbers(numbers));
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

	private List<Integer> splitNumbers(String numbers) {
		return Arrays.stream(numbers.split(LOTTERY_NUMBER_DELIMITER))
			.map(String::trim)
			.map(Integer::parseInt)
			.collect(Collectors.toList());
	}

	private void validateNumber(String number) {
		if (!NUMBER_PATTERN.matcher(number).matches()) {
			throw new IllegalArgumentException(INVALID_INPUT_NUMBER_EXCEPTION.getMessage());
		}
	}

	private void validateLotteryNumber(String lotteryNumber) {
		if (!LOTTERY_NUMBER_PATTERN.matcher(lotteryNumber).matches()) {
			throw new IllegalArgumentException(INVALID_WINNING_NUMBER_EXCEPTION.getMessage());
		}
	}

	public int inputValidBonusNumber() {
		System.out.println(INPUT_BONUS_BALL_MESSAGE.getMessage());
		try {
			String bonusNumber = inputBonusNumber();
			return Integer.parseInt(bonusNumber);
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputValidBonusNumber();
		}
	}

	private String inputBonusNumber() {
		final String bonusNumber = scanner.nextLine();
		validateNumber(bonusNumber);
		return bonusNumber;
	}
}
