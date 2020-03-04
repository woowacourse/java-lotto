package lotto.view;

import lotto.util.Spliter;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_MANUAL_LOTTO_NUMBER_MESSAGE = "수동으로 구매할 번호를 입력해주세요.";
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해주세요.";
    private static final String INPUT_MANUAL_LOTTO_COUNT_MESSAGE = "수동으로 구매할 로또 개수를 입력해주세요.";
    private static final String INTEGER_INPUT_ERROR_MESSAGE = "공백을 포함한 문자가 입력되었습니다. 숫자만 입력해주세요.";
    private static final String INPUT_WINNING_LOTTO_NUMBER_MESSAGE = "당첨 번호를 입력해주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해주세요.";


    public static int inputManualTicketNumber(){
        System.out.println(INPUT_MANUAL_LOTTO_COUNT_MESSAGE);
        return inputNumber();
    }

    public static int inputMoney(){
        System.out.println(INPUT_MONEY_MESSAGE);
        return inputNumber();
    }

    public static int inputBonus(){
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        return inputNumber();
    }

    private static int inputNumber(){
        int number;
        try{
            number = Integer.parseInt(SCANNER.nextLine());
        }catch (NumberFormatException e){
            throw new NumberFormatException(INTEGER_INPUT_ERROR_MESSAGE);
        }
        System.out.println();
        return number;
    }

    public static List<Integer> inputWinningLottoNumbers(){
        System.out.println(INPUT_WINNING_LOTTO_NUMBER_MESSAGE);
        return inputLottoNumbers();
    }

    public static List<Integer> inputManualLottoNumbers() {
        System.out.println(INPUT_MANUAL_LOTTO_NUMBER_MESSAGE);
        return inputLottoNumbers();
    }

    private static List<Integer> inputLottoNumbers() {
        String input = SCANNER.nextLine();
        System.out.println();
        return Spliter.splitInput(input);
    }
}
