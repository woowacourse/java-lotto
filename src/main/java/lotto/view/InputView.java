package lotto.view;

import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.exception.InvalidLottoNumbersException;
import lotto.exception.InvalidPaymentException;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static Money inputMoney() {
        System.out.println("구입 금액을 입력해 주세요.");
        try {
            return new Money(SCANNER.nextLine());
        } catch (InvalidPaymentException e) {
            System.out.println(e.getMessage());
            return inputMoney();
        }
    }

    public static WinningLotto inputWinningLotto() {
        String lottoNumbers = inputWinningLottoNumbers();
        String bonusNumber = inputWinningLottoBonusNumber();

        try {
            return new WinningLotto(lottoNumbers, bonusNumber);
        } catch (InvalidLottoNumbersException e) {
            System.out.println(e.getMessage());
            return inputWinningLotto();
        }
    }

    private static String inputWinningLottoNumbers() {
        System.out.println("지난주 당첨 번호를 입력해 주세요.");
        return SCANNER.nextLine();
    }

    private static String inputWinningLottoBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return SCANNER.nextLine();
    }
}
