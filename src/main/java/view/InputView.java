package view;

import static view.InputMessage.BONUS;
import static view.InputMessage.PRICE;
import static view.InputMessage.WINNING_LOTTO;

import java.util.List;
import java.util.Scanner;
import view.parser.InputParser;

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
