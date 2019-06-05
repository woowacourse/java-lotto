package lotto.view;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static Money getMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        try {
            int money = Integer.parseInt(SCANNER.nextLine());
            return new Money(money);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return getMoney();
        }
    }

    public static int getNumberOfManualLotto(Money money) {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        try {
            int numberOfManualLotto = Integer.parseInt(SCANNER.nextLine());
            validateNumberOfManualLotto(numberOfManualLotto, money);
            return numberOfManualLotto;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return getNumberOfManualLotto(money);
        }
    }

    private static void validateNumberOfManualLotto(int numberOfManualLotto, Money money) {
        if (numberOfManualLotto < 0) {
            throw new IllegalArgumentException("0 이상의 숫자만 입력할 수 있습니다.");
        }
        if (numberOfManualLotto > money.getNumberOfLotto()) {
            throw new IllegalArgumentException("구매 금액을 초과하는 숫자입니다.");
        }
    }

    public static List<Lotto> getManualLotto(int numberOfManualLotto) {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        List<Lotto> lottos = new ArrayList<>();
        while (numberOfManualLotto-- > 0) {
            lottos.add(getUserLotto());
        }
        return lottos;
    }

    private static Lotto getUserLotto() {
        try {
            List<String> lottoNumbersInput = Arrays.asList(SCANNER.nextLine().split(","));
            return ManualLottoGenerator.generate(lottoNumbersInput);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return getUserLotto();
        }
    }

    public static WinningLotto getWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        Lotto lotto = getUserLotto();
        LottoNumber bonusBall = getBonusBall();
        try {
            return new WinningLotto(lotto, bonusBall);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return getWinningLotto();
        }
    }

    private static LottoNumber getBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        try {
            int bonusBall = Integer.parseInt(SCANNER.nextLine());
            return new LottoNumber(bonusBall);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return getBonusBall();
        }
    }
}
