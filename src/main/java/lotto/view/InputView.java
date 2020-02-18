package lotto.view;

import java.util.Scanner;

public class InputView {
    Scanner scanner = new Scanner(System.in);

    public String InputPurchaseAmount(){
        return scanner.nextLine();
    }
}
