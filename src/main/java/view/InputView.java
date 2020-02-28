package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String COMMA = ",";

    public static String inputPurchaseAmount(){
        OutputView.printInputPurchaseAmountMessage();
        return scanner.nextLine();
    }

    public static String[] inputWinningNumbers() {
        OutputView.printInputWinningNumbersMessage();
        return scanner.nextLine()
                    .split(COMMA);
    }

    public static String inputBonusNumber() {
        OutputView.printInputBonusNumberMessage();
        return scanner.nextLine();
    }

    public static String inputManualLottoCount() {
        return scanner.nextLine();
    }

    public static List<List<String>> inputManualLottoNumbers(int manualLottoCount) {
        OutputView.printInputManualLottoNumbers();
        List<List<String>> manualLottoNumbers = new ArrayList<>();
        for (int i = 0; i < manualLottoCount; i++) {
            manualLottoNumbers.add(inputOneManualLottoNumbers());
        }
        return manualLottoNumbers;
    }

    private static List<String> inputOneManualLottoNumbers() {
        List<String> oneManualLottoNumber = new ArrayList<>();
        String lottoNumbers = scanner.nextLine();
        for (String number : lottoNumbers.split(COMMA)) {
            oneManualLottoNumber.add(number);
        }
        return oneManualLottoNumber;
    }
}
