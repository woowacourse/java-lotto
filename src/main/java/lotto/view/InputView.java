package lotto.view;

import java.util.Scanner;

public class InputView {
    static Scanner scanner = new Scanner(System.in);

    public static String InputPurchaseAmount(){
        return scanner.nextLine();
    }
}
