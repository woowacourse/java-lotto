package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.util.InputUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInputView {
    public static String promptBuyingMoney() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String promptManualLottoQuantity() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static List<String> promptManualLottoNumbers(LottoQuantity manualLottoQuantity) {
        List<String> lottoNums = new ArrayList<>();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < manualLottoQuantity.toInt(); i++) {
            lottoNums.add(promptSingleManualLottoNumbers());
        }

        return lottoNums;
    }

    private static String promptSingleManualLottoNumbers() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String promptWinningNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String promptWinningBonusNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }
}
