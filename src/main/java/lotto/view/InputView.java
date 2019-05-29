package lotto.view;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.Number;
import lotto.domain.WinningLotto;
import lotto.util.StringUtil;

import java.util.Scanner;

public class InputView {
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

    public static Lotto createLotto() {
        try {
            return new Lotto(getNumbers());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요");
            return createLotto();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createLotto();
        }
    }

    private static List<Number> getNumbers() {
        List<Number> lotto = new ArrayList<>();
        String[] lottoString = inputUserString();
        for (String num : lottoString) {
            lotto.add(Number.valueOf(Integer.parseInt(num)));
        }
        return lotto;
    }

    private static String[] inputUserString() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return StringUtil.parseString(scanner.nextLine());
    }


    public static Number createBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String userInput = scanner.nextLine();
        try {
            return Number.valueOf(Integer.parseInt(userInput));
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요");
            return createBonusNumber();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createBonusNumber();
        }
    }

    public static WinningLotto createWinningLotto(Lotto lotto) {
        try {
            return new WinningLotto(lotto, InputView.createBonusNumber());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createWinningLotto(lotto);
        }

    }
}
