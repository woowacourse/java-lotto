package lotto.view;

import lotto.domain.number.LottoNumber;
import lotto.domain.number.PurchaseNumber;
import lotto.domain.number.WinningNumbers;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_PURCHASE_NUMBER_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INVALID_PURCHASE_NUMBER_MESSAGE = "숫자가 아닌 입력이 들어왔습니다.";

    public static PurchaseNumber inputPurchaseMoney() {
        try {
            System.out.println(INPUT_PURCHASE_NUMBER_MESSAGE);
            String input = SCANNER.nextLine();
            return PurchaseNumber.calculate(Integer.parseInt(input));
        } catch (InputMismatchException e) {
            System.out.println(INVALID_PURCHASE_NUMBER_MESSAGE);
            return inputPurchaseMoney();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputPurchaseMoney();
        }
    }

    public static WinningNumbers inputWinningNumbers() {
        List<LottoNumber> winningNumbers;
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String inputWinningNumbers = SCANNER.nextLine();
        String[] inputWinningNumber = inputWinningNumbers.replace(" ", "").split(",");
        winningNumbers = Arrays.stream(inputWinningNumber)
                .map(Integer::parseInt)
                .map(LottoNumber::of)
                .collect(Collectors.toList());
        System.out.println("보너스 볼을 입력해 주세요.");
        LottoNumber bonusBall = LottoNumber.of(SCANNER.nextInt());
        return new WinningNumbers(winningNumbers, bonusBall);
    }
}
