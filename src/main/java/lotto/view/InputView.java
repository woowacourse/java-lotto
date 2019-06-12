package lotto.view;

import lotto.domain.NumberOfCustomLotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static lotto.view.OutPutView.ENTER;

public class InputView {
    private static final String SPLIT_REGEX = ",";
    private static Scanner scanner = new Scanner(System.in);

    public static String InputPrice() {
        System.out.println("구매금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String InputNumberOfCustomLotto() {
        System.out.println(ENTER + "수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static List<String> InputCustomLottoNumber(NumberOfCustomLotto numberOfCustomLotto) {
        System.out.println(ENTER + "수동으로 구매할 번호를 입력해 주세요.");
        List<String> customLottos = new ArrayList<>();

        for (int i = 0; i < numberOfCustomLotto.getNumberOfCustomLotto(); i++) {
            customLottos.add(scanner.nextLine());
        }

        return customLottos;
    }

    public static String[] InputWinLottoNumber() {
        System.out.println(ENTER + "지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine().split(SPLIT_REGEX);
    }

    public static String InputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }
}
