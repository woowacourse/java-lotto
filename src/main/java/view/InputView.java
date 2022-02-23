package view;

import static utils.Messages.*;

import java.util.Scanner;

import validator.InputValidator;

public class InputView {
	public static int inputMoney() {
		System.out.println(MONEY_INPUT_MESSAGE);
		Scanner scanner = new Scanner(System.in);
		try {
			return scanner.nextInt();
		} catch (Exception e) {
			throw new IllegalArgumentException(NUM_ERROR_MESSAGE);
		}
	}

	public static String inputWinLottoNumbers() {
		System.out.println(LOTTO_NUMBER_INPUT_MESSAGE);
		Scanner scanner = new Scanner(System.in);
		String lottoNumbers = scanner.nextLine();
		InputValidator.isRightPattern(lottoNumbers);
		return lottoNumbers;
	}

	public static int inputBonusNumber() {
		System.out.println(BONUS_INPUT_MESSAGE);
		Scanner scanner = new Scanner(System.in);
		try {
			return scanner.nextInt();
		} catch (Exception e) {
			throw new IllegalArgumentException(NUM_ERROR_MESSAGE);
		}
	}
}