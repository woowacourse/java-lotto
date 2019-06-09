package lotto.view;

import lotto.domain.creator.ManualLottoCreator;
import lotto.domain.Money;
import lotto.domain.lotto.WinningLotto;
import lotto.exception.InvalidInputException;
import lotto.exception.InvalidLottoNumbersException;
import lotto.exception.InvalidPaymentException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
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

    public static int inputManualLotto(Money money) {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String input = SCANNER.nextLine();

        try {
            checkIsBlank(input);
            int number = parseInt(input);
            checkIsBuyable(money, number);
            return number;
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return inputManualLotto(money);
        }

    }

    private static void checkIsBuyable(Money money, int number) {
        int buyableLottoQuantity = money.getBuyableLottoQuantity();
        if (number > buyableLottoQuantity) {
            throw new InvalidInputException(
                    String.format("구매 가능한 수량을 초과하였습니다. 최대 %d개 구매 가능합니다.", buyableLottoQuantity));
        }
    }

    private static void checkIsBlank(String input) {
        if (StringUtils.isBlank(input)) {
            throw new InvalidInputException("아무것도 입력하지 않으셨습니다.");
        }
    }

    private static int parseInt(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("숫자자 아닌 값이 포함되어 있습니다.");
        }
    }

    public static ManualLottoCreator generateManualLottoCreator(int numberOfManualLotto) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<String> numbers = collectManualLottoNumber(numberOfManualLotto);

        try {
            return new ManualLottoCreator(numbers);
        } catch (InvalidLottoNumbersException e) {
            System.out.println(e.getMessage());
            return generateManualLottoCreator(numberOfManualLotto);
        }
    }

    private static String inputManualLottoNumber() {
        return SCANNER.nextLine();
    }

    private static List<String> collectManualLottoNumber(int numberOfManualLotto) {
        List<String> numbers = new ArrayList<>();

        for (int i = 0; i < numberOfManualLotto; i++) {
            numbers.add(inputManualLottoNumber());
        }
        return numbers;
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
