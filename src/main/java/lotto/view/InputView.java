package lotto.view;

import lotto.domain.exception.PurchaseMoneyLackException;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.PurchaseNumber;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_PURCHASE_NUMBER_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INVALID_PURCHASE_NUMBER_MESSAGE = "숫자가 아닌 입력이 들어왔습니다.";
    private static final String INPUT_WINNING_NUMBER_HEADER = "지난 주 당첨 번호를 입력해 주세요. \n당첨 번호 사이에 , 를 넣어주세요!";
    private static final String INPUT_BONUS_NUMBER_HEADER = "보너스 볼을 입력해 주세요.";
    private static final String NUMBER_FORMAT_MISMATCH_EXCEPTION_PREFIX_MESSAGE = "숫자를 입력해주세요.";
    public static final String DELIMITER = ",";
    private static final String BLANK = " ";
    private static final String NO_BLANK = "";

    public static PurchaseNumber inputPurchaseMoney() {
        try {
            System.out.println(INPUT_PURCHASE_NUMBER_MESSAGE);
            String input = SCANNER.nextLine();
            return PurchaseNumber.calculate(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            System.out.println(INVALID_PURCHASE_NUMBER_MESSAGE);
            return inputPurchaseMoney();
        } catch (PurchaseMoneyLackException e) {
            System.out.println(e.getMessage());
            return inputPurchaseMoney();
        }
    }

    public static List<LottoNumber> inputWinningNumbers() {
        try {
            System.out.println(INPUT_WINNING_NUMBER_HEADER);
            return Arrays.stream(deleteBlankAndSplit(SCANNER.nextLine()))
                    .map(Integer::parseInt)
                    .map(LottoNumber::of)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println(NUMBER_FORMAT_MISMATCH_EXCEPTION_PREFIX_MESSAGE);
            return inputWinningNumbers();
        }
    }

    public static LottoNumber inputBonusNumber() {
        try {
            System.out.println(INPUT_BONUS_NUMBER_HEADER);
            int inputNumber = Integer.parseInt(SCANNER.nextLine());
            return LottoNumber.of(inputNumber);
        } catch (NumberFormatException e) {
            System.out.println(NUMBER_FORMAT_MISMATCH_EXCEPTION_PREFIX_MESSAGE);
            return inputBonusNumber();
        }
    }

    private static String[] deleteBlankAndSplit(String string) {
        return string.replace(BLANK, NO_BLANK).split(DELIMITER);
    }
}
