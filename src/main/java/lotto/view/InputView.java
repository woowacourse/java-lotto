package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputMoney(){
        OutputView.printInputMoney();
        return SCANNER.nextLine();
    }

    public static String inputManualLottoCount(){
        OutputView.printInputManualLottoCount();
        return SCANNER.nextLine();
    }

    public static String inputManualLottoTicket(){
        return SCANNER.nextLine();
    }

    public static String inputWinningLottoTicket(){
        OutputView.printInputWinningLottoTicket();
        return SCANNER.nextLine();
    }

    public static String inputBonusBall(){
        OutputView.printInputBonusBall();
        return SCANNER.nextLine();
    }
}
