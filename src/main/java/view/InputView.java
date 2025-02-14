package view;

import static view.InputMessage.*;

import java.util.Scanner;

public class InputView {

    private final Scanner sc;

    public InputView() {
        sc = new Scanner(System.in);
    }

    public String inputPrice() {
        System.out.println(PRICE);
        return sc.nextLine();
    }

    public String inputWinningLotto() {
        System.out.println(WINNING_LOTTO);
        return sc.nextLine();
    }

    public String inputBonusLotto() {
        System.out.println(BONUS);
        return sc.nextLine();
    }
}
