package lotto.view;

import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.domain.Money;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static Money askMoney() {
        OutputView.printMessage("구입 금액을 입력해 주세요.");
        String money = scanner.nextLine();
        try {
            return new Money(money);
        } catch (Exception e) {
            OutputView.printError(e);
            return askMoney();
        }
    }

    public static String askFixedLottoQuantity() {
        OutputView.printMessage("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static List<LottoNumbers> askFixLottoNumbersBundle(int fixedLottoQuantity) {
        OutputView.printMessage("수동으로 구매할 번호를 입력해 주세요.");
        List<LottoNumbers> FixLottoNumbersBundle = new ArrayList<>();
        for (int i = 0; i < fixedLottoQuantity; i++) {
            String input = scanner.nextLine();
            FixLottoNumbersBundle.add(createWinningLottoNumbers(input));
        }
        return FixLottoNumbersBundle;
    }

    public static LottoNumbers askLastWinningLottoNumbers() {
        OutputView.printMessage("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();
        try {
            return createWinningLottoNumbers(input);
        } catch (Exception e) {
            OutputView.printError(e);
            return askLastWinningLottoNumbers();
        }
    }

    private static LottoNumbers createWinningLottoNumbers(String input) {
        List<String> splitNumbers = Arrays.asList(input.split(","));
        List<LottoNumber> lottoNumbers = splitNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new LottoNumbers(lottoNumbers);
    }


    public static LottoNumber askBonusNumber() {
        OutputView.printMessage("보너스 볼을 입력해 주세요.");
        String bonusNumber = scanner.nextLine();
        try {
            return new LottoNumber(bonusNumber);
        } catch (Exception e) {
            OutputView.printError(e);
            return askBonusNumber();
        }
    }
}
