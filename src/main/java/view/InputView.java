package view;

import java.util.Scanner;

import domain.Lotto;
import domain.LottoNumber;
import domain.Payment;
import domain.WinningLotto;

/**
 * 유틸 클래스를 했을 때 이점
 * - 사용성 증가..
 * 객체를 만들면 장점
 * - 입력 받는 곳을 제한할 수 있다!
 */

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);

	public Payment insertPayment() {
		try {
			System.out.println("구입금액을 입력해 주세요.");
			return new Payment(scanner.nextLine());
		} catch (Exception e) {
			System.out.println("[ERROR] " + e.getMessage());
			return insertPayment();
		}
	}

	public Lotto insertLotto() {
		try {
			System.out.println("지난 주 당첨 번호를 입력해 주세요.");
			String inputValue = scanner.nextLine();
			String[] numbers = inputValue.split(",");
			return Lotto.of(numbers);
		} catch (Exception e) {
			System.out.println("[ERROR] " + e.getMessage());
			return insertLotto();
		}
	}

	public WinningLotto insertBonus(Lotto lotto) {
		try {
			System.out.println("보너스 볼을 입력해 주세요.");
			return new WinningLotto(lotto, new LottoNumber(scanner.nextLine()));
		} catch (Exception e) {
			System.out.println("[ERROR] " + e.getMessage());
			return insertBonus(lotto);
		}
	}
}
