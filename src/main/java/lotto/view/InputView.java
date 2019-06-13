package lotto.view;

import lotto.domain.Money;
import lotto.domain.creator.CreatorFactory;
import lotto.domain.creator.LottoCreator;
import lotto.domain.lotto.LottoFactory;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.util.CustomStringUtils;
import lotto.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static Money inputMoney() {
        System.out.println("구입 금액을 입력해 주세요.");
        try {
            return new Money(SCANNER.nextLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputMoney();
        }
    }

    public static int inputManualLotto(Money money) {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String input = SCANNER.nextLine();

        try {
            CustomStringUtils.checkIsBlank(input);
            int number = CustomStringUtils.parseInt(input);
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

    public static Lottos generateLottos(int manualLottoQuantity, int autoLottoQuantity) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        try {
            CreatorFactory factory = new CreatorFactory(collectManualLottoNumber(manualLottoQuantity));
            List<LottoCreator> creators = factory.createCreators(manualLottoQuantity, autoLottoQuantity);
            return LottoFactory.createLottos(creators);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return generateLottos(manualLottoQuantity, autoLottoQuantity);
        }
    }

    private static List<String> collectManualLottoNumber(int numberOfManualLotto) {
        List<String> numbers = new ArrayList<>();

        for (int i = 0; i < numberOfManualLotto; i++) {
            numbers.add(inputManualLottoNumber());
        }
        return numbers;
    }

    private static String inputManualLottoNumber() {
        try {
            String input = SCANNER.nextLine();
            CustomStringUtils.checkIsBlank(input);
            return input;
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return inputManualLottoNumber();
        }
    }

    public static WinningLotto inputWinningLotto() {
        String lottoNumbers = inputWinningLottoNumbers();
        String bonusNumber = inputWinningLottoBonusNumber();

        try {
            return new WinningLotto(lottoNumbers, bonusNumber);
        } catch (Exception e) {
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
