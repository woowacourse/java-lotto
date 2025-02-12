package view;

import java.util.Scanner;

public class InputView {

    private final Scanner sc;

    public InputView() {
        sc = new Scanner(System.in);
    }

    public String inputPrice() {
        System.out.println(Input.PRICE.getMessage());
        return sc.nextLine();
    }

    public String inputWinningLotto() {
        System.out.println(Input.WINNING_LOTTO.getMessage());
        return sc.nextLine();
    }

    public String inputBonusLotto() {
        System.out.println(Input.BONUS.getMessage());
        return sc.nextLine();
    }
}
