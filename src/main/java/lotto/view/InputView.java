package lotto.view;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.Number;
import lotto.domain.WinningLotto;

import java.util.Scanner;

public class InputView {
    private static final String SPLIT_DELIMITER = ",";
    private static final String REPLACE_DELIMITER = "";
    private static final String REPLACE_STRING = " ";

    private static final Scanner scanner = new Scanner(System.in);

    public static Money createMoney() {
        System.out.println("구입금액을 입력해 주세요.");

        try {
            return new Money(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요");
            return createMoney();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createMoney();
        }
    }

    public static WinningLotto createWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String userInput = scanner.nextLine();
        List<Number> lotto = new ArrayList<>();
        try {
            String[] lottoString = userInput.replaceAll(REPLACE_STRING, REPLACE_DELIMITER).split(SPLIT_DELIMITER);
            for (String string : lottoString) {
                lotto.add(Number.getInstance(Integer.parseInt(string)));
            }
            return new WinningLotto(new Lotto(lotto));
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요");
            return createWinningLotto();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createWinningLotto();
        }
    }
}
