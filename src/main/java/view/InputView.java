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

    public List<Integer> inputWinningLotto() {
        System.out.println(WINNING_LOTTO);

        String inputWinningLotto = sc.nextLine();

        return InputParser.parseWinningLotto(inputWinningLotto);
    }

    public String inputBonusLotto() {
        System.out.println(BONUS);
        return sc.nextLine();
    }
}
