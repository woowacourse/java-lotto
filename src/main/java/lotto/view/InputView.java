package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoNo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String SPLITTER = ",";

    private static final Scanner sc = new Scanner(System.in);

    public static int inputBudget() {
        try {
            System.out.println("구입 금액을 입력해주세요.");
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException nfe) {
            System.out.println("정수가 아닙니다. 다시 입력해주세요." + NEW_LINE);
            return inputBudget();
        }
    }

    public static List<Integer> inputWinningLotto() {
        try {
            System.out.println("당첨 번호를 입력해주세요.");
            return Arrays.stream(sc.nextLine().replaceAll(" ", "").split(SPLITTER))
                    .map(Integer::parseInt).collect(Collectors.toList());
        } catch (NumberFormatException nfe) {
            System.out.println("정수가 아닙니다. 다시 입력해주세요." + NEW_LINE);
            return inputWinningLotto();
        }
    }

    public static int inputBonusNo() {
        try {
            System.out.println(NEW_LINE + "보너스 번호를 입력해주세요.");
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException nfe) {
            System.out.println("정수가 아닙니다. 다시 입력해주세요." + NEW_LINE);
            return inputBonusNo();
        }
    }

    public static int inputCountOfManualLotto() {
        try {
            System.out.println(NEW_LINE + "수동으로 구매할 로또 수를 입력해 주세요.");
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException nfe) {
            System.out.println("정수가 아닙니다. 다시 입력해주세요." + NEW_LINE);
            return inputCountOfManualLotto();
        }
    }

    public static List<Lotto> inputManualLottos(int countOfManualLottos) {
        System.out.println(NEW_LINE + "수동으로 구매할 번호를 입력해 주세요.");
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < countOfManualLottos; i++) {
            lottos.add(inputManualLotto());
        }
        return lottos;
    }

    private static Lotto inputManualLotto() {
        try {
            return Lotto.of(Arrays.stream(sc.nextLine().replaceAll(" ", "").split(SPLITTER))
                    .map(Integer::parseInt).map(LottoNo::new).collect(Collectors.toList()));
        } catch (NumberFormatException nfe) {
            System.out.println("정수가 아닙니다. 다시 입력해주세요.");
            return inputManualLotto();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputManualLotto();
        }
    }
}
