package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import domain.LottoNumber;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);

	public static int inputPurchaseMoney() {
		System.out.println("구입금액을 입력해 주세요.");
		return Integer.parseInt(scanner.nextLine());
	}

	public static List<LottoNumber> inputSixNumbers() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		return Arrays.stream(scanner.nextLine().split(", "))
			.map(Integer::parseInt)
			.map(LottoNumber::new)
			.collect(Collectors.toList());
	}

	public static LottoNumber inputBonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return new LottoNumber(Integer.parseInt(scanner.nextLine()));
	}
}
