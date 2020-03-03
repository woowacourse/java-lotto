package Lotto.views;

import Lotto.domain.LottoCount;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String INPUT_PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String INPUT_WINNING_LOTTO_MESSAGE = "지난 주 당첨번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";
    private static final int ZERO = 0;

    private static Scanner scanner = new Scanner(System.in);

    public static String inputAsPurchaseAmount() {
        System.out.println(INPUT_PURCHASE_AMOUNT_MESSAGE);
        return scanner.nextLine();
    }

    public static String inputAsManualLottoCount() {
        System.out.println(INPUT_MANUAL_LOTTO_COUNT_MESSAGE);
        return scanner.nextLine();
    }

    public static List<String> inputAsManualLotto(LottoCount manualLottoCount) {
        if (manualLottoCount.getLottoCount() == ZERO) return null;
        System.out.println(INPUT_MANUAL_LOTTO_MESSAGE);

        List<String> manualLottoInput = new ArrayList<>();
        for(int i = ZERO; i < manualLottoCount.getLottoCount(); i++) {
            manualLottoInput.add(scanner.nextLine());
        }
        return manualLottoInput;
    }

    public static String inputAsWinningLotto() {
        System.out.println(INPUT_WINNING_LOTTO_MESSAGE);
        return scanner.nextLine();
    }

    public static String inputAsBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        return scanner.nextLine();
    }
}