package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.PurchaseAmount;
import lotto.domain.PurchaseLottos;
import lotto.domain.WinningRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String INPUT_PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_AMOUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    private static final int LOTTO_PRICE = 1000;

    private static final Scanner SCANNER = new Scanner(System.in);

    public static PurchaseAmount inputPurchaseAmount() {
        System.out.println(INPUT_PURCHASE_AMOUNT_MESSAGE);
        int purchaseAmount = Integer.parseInt(SCANNER.nextLine()) / LOTTO_PRICE;

        System.out.println(INPUT_MANUAL_LOTTO_AMOUNT_MESSAGE);
        int manualLottoAmount = Integer.parseInt(SCANNER.nextLine());

        return new PurchaseAmount(purchaseAmount - manualLottoAmount, manualLottoAmount);
    }

    public static List<Lotto> inputManualLotto(int manualLottoAmount) {
        System.out.println(INPUT_MANUAL_LOTTO_MESSAGE);
        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < manualLottoAmount; i++) {
            List<Integer> manualLotto = inputLotto();
            manualLottos.add(new Lotto(manualLotto));
        }
        return manualLottos;
    }

    public static WinningRule inputWinningNumbers() {
        List<Integer> winningNumbers;
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        winningNumbers = inputLotto();
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
        int bonusBall = SCANNER.nextInt();
        return new WinningRule(winningNumbers, bonusBall);
    }

    private static List<Integer> inputLotto() {
        return Arrays.stream(SCANNER.nextLine().trim().split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
