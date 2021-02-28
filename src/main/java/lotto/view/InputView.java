package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    public static final String DELIMITER = ",";
    private static final String BLANK = " ";
    private static final String REPLACEMENT = "";
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String inputMoney() {
        OutputView.printInputMoneyMessage();
        return SCANNER.nextLine();
    }

    public static List<String> inputManualLottoTickets(int manualLottoAmount) {
        List<String> lottoTicketsInput = new ArrayList<>();
        for (int i = 0; i < manualLottoAmount; i++) {
            lottoTicketsInput.add(SCANNER.nextLine()
                    .replace(BLANK, REPLACEMENT));
        }
        return lottoTicketsInput;
    }

    public static List<String> inputWinningNumbers() {
        OutputView.printInputWinningNumbers();
        return Arrays.asList(SCANNER.nextLine()
                .replace(BLANK, REPLACEMENT)
                .split(DELIMITER));
    }

    public static String inputBonusNumber() {
        OutputView.printInputBonusNumberMessage();
        return SCANNER.nextLine();
    }

    public static String inputManualLottoAmount() {
        OutputView.printInputManualLottoAmountMessage();
        return SCANNER.nextLine();
    }
}
