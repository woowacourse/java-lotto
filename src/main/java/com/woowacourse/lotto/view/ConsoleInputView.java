package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.BuyingMoney;
import com.woowacourse.lotto.domain.WinningLotto;
import com.woowacourse.lotto.util.InputUtil;

import java.util.HashSet;
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
            return new WinningLotto(new HashSet<>(InputUtil.splitByComma(winningNumberStr)), Integer.valueOf(bonusStr));
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 입력입니다.");
            return null;
        }
    }
}
