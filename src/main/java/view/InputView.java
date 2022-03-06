package view;

import domain.AutoLottoGenerator;
import domain.Lotto;
import domain.LottoGenerator;
import domain.LottoNumber;
import domain.ManualLottoGenerator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import util.StringUtil;

public class InputView {

    private static final String QUESTION_MONEY_INPUT = "구매금액을 입력해 주세요.";
    private static final String QUESTION_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String QUESTION_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String QUESTION_PICK_LOTTO = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String QUESTION_NUMBER_OF_PICK_LOTTO = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String NUMBER_DELIMITER = ", ";
    private static final Scanner scanner = new Scanner(System.in);

    public static int askMoneyInput() {
        System.out.println(QUESTION_MONEY_INPUT);
        String moneyInput = scanner.nextLine();
        return StringUtil.convertToInteger(moneyInput);
    }

    public static int askNumberOfManualLotto() {
        System.out.println(QUESTION_PICK_LOTTO);
        return StringUtil.convertToInteger(scanner.nextLine());
    }

    public static void askManualLottoNumbers() {
        System.out.println(QUESTION_NUMBER_OF_PICK_LOTTO);
    }

    public static List<Integer> askWinningNumbers() {
        System.out.println(QUESTION_WINNING_NUMBERS);
        return askLottoNumbers();
    }

    public static int askBonusNumber() {
        System.out.println(QUESTION_BONUS_NUMBER);
        return StringUtil.convertToInteger(scanner.nextLine());
    }

    private static List<Integer> validateAndCovertNumbersToInteger(List<String> numbers) {
        return numbers.stream()
                .map(StringUtil::convertToInteger)
                .collect(Collectors.toList());
    }

    public static List<Integer> askLottoNumbers() {
        List<String> lottoNumbers = List.of(scanner.nextLine().split(NUMBER_DELIMITER));
        return validateAndCovertNumbersToInteger(lottoNumbers);
    }
}
