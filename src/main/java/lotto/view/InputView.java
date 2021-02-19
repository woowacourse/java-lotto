package lotto.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String getMoneyInput() {
        OutputView.askHowMuchToBuy();
        return getUserInput();
    }

    public static String getWinnerNumbersInput() {
        OutputView.askWinnerLottoTicket();
        return getUserInput();
    }

    public static String getBonusNumberInput() {
        OutputView.askWinnerBonusNumber();
        return getUserInput();
    }

    private static String getUserInput() {
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
