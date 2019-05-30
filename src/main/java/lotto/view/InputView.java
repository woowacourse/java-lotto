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

    public static int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요");
            return inputMoney();
        }
    }

    public static int inputManualSize() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        try {
            int manualSize = Integer.parseInt(scanner.nextLine());
            return manualSize;
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요");
            return inputManualSize();
        }
    }

    public static List<String[]> createManualLottos(int manualSize) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<String[]> result = new ArrayList<>();
        for (int i = 0; i < manualSize; i++) {
            result.add(StringUtil.parseString(scanner.nextLine()));
        }
        return result;
    }

    public static String[] inputUserString() {
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
