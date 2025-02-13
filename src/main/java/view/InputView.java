package view;

import static view.util.LottoConstants.COMMA_REGEX;
import static view.util.LottoConstants.DELIMITER_VALIDATE_REGEX;
import static view.util.LottoConstants.NUMERIC_VALIDATE_REGEX;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import view.util.LottoConstants;

public class InputView {
    private static final String INPUT_MONEY_PROMPT = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_PROMPT = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_PROMPT = "보너스 번호를 입력해 주세요.";
    private static Scanner scanner = new Scanner(System.in);

    private static void isDivideByThousand(int inputMoney) {
        if (inputMoney % LottoConstants.LOTTO_PRICE_PER_ONE != 0) {
            throw new IllegalArgumentException("천원단위로 입력해주세요.");
        }
    }

    private static void isNumeric(String inputMoneyString) {
        if (!inputMoneyString.matches(NUMERIC_VALIDATE_REGEX)) {
            throw new IllegalArgumentException("구입 금액은 숫자여야 합니다.");
        }
    }

    public static int inputAndValidateUserMoney() {
        System.out.println(INPUT_MONEY_PROMPT);
        String userInputMoney = read();
        try {
            int inputMoney = parseInt(userInputMoney);
            isDivideByThousand(inputMoney);
            return inputMoney;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputAndValidateUserMoney();
        }
    }


    private static String read() {
        return scanner.nextLine();
    }

    public static List<Integer> inputWinningNumbers(){
        System.out.println(INPUT_WINNING_NUMBER_PROMPT);
        try {
            String input = read();
            isValidDelimiter(input);
            return splitWinningNumbers(input);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return inputWinningNumbers();
        }
    }

    private static int parseInt(String input){
        isNumeric(input);
        return Integer.parseInt(input);
    }

    private static void isValidDelimiter(String input){
        String regex = DELIMITER_VALIDATE_REGEX;
        if(!input.matches(regex)){
            throw new IllegalArgumentException("당첨번호 형식이 올바르지 않습니다.");
        }
    }

    private static List<Integer> splitWinningNumbers(String input){
        List<Integer> winningNumbers = new ArrayList<>();

        for (String value : input.split(COMMA_REGEX)) {
            winningNumbers.add(Integer.parseInt(value));
        }

        return  winningNumbers;
    }

    public static int isNumericBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_PROMPT);
        String bonusNumberString = read();
        try {
            return parseInt(bonusNumberString);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return isNumericBonusNumber();
        }
    }
}
