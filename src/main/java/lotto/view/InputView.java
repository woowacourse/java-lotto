package lotto.view;

import java.util.Scanner;
import lotto.domain.Money;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);
    public Money inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return new Money(scanner.nextLine());
    }

    public String inputWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public String inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }

    public void closeScanner() {
        scanner.close();
    }

}
