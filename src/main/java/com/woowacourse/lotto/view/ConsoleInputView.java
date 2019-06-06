package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.util.InputUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInputView {
    public static BuyingMoney promptBuyingMoney() {
        BuyingMoney bm = tryCreateBuyingMoney();
        while (bm == null) {
            bm = tryCreateBuyingMoney();
        }
        return bm;
    }

    private static BuyingMoney tryCreateBuyingMoney() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("구입금액을 입력해 주세요.");
            return new BuyingMoney(Integer.parseInt(scanner.nextLine()));
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 입력입니다.");
            return null;
        }
    }

    public static WinningLotto promptWinningNumber() {
        WinningLotto winningLotto = tryCreateWinningLotto();
        while (winningLotto == null) {
            winningLotto = tryCreateWinningLotto();
        }
        return winningLotto;
    }

    private static WinningLotto tryCreateWinningLotto() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            String winningNumberStr = scanner.nextLine();
            System.out.println("보너스 볼을 입력해 주세요.");
            String bonusStr = scanner.nextLine();
            return new WinningLotto(LottoNumberGroup.of(InputUtil.splitByComma(winningNumberStr)), Integer.valueOf(bonusStr));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static LottoQuantity promptManualLottoQuantity() {
        LottoQuantity manualLottoQuantity = tryPromptManualLottoQuantity();
        while (manualLottoQuantity == null) {
            manualLottoQuantity = tryPromptManualLottoQuantity();
        }

        return manualLottoQuantity;
    }

    private static LottoQuantity tryPromptManualLottoQuantity() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
            return LottoQuantity.of(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static List<Lotto> promptManualLottoNumbers(LottoQuantity manualLottoQuantity) {
        List<Lotto> lottos = tryCreateManualLotto(manualLottoQuantity);

        while (lottos == null) {
            lottos = tryCreateManualLotto(manualLottoQuantity);
        }

        return lottos;
    }

    private static List<Lotto> tryCreateManualLotto(LottoQuantity manualLottoQuantity) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("수동으로 구매할 번호를 입력해 주세요.");
            return promptManualLotto(scanner, manualLottoQuantity);
        } catch (NumberFormatException e) {
            System.out.println("숫자로 변환할 수 없는 문자열이 있습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static List<Lotto> promptManualLotto(Scanner scanner, LottoQuantity quantity) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < quantity.toInt(); i++) {
            lottos.add(LottoFactory.createLotto(LottoNumberGroup.of(InputUtil.splitByComma(scanner.nextLine()))));
        }

        return lottos;
    }
}
