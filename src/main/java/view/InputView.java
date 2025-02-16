package view;

import domain.DrawResult;
import domain.LottoNumber;
import java.util.Arrays;
import java.util.List;
import util.Console;
import util.RetryHandler;

public class InputView {
    public static int inputPurchaseAmount() {
        return (Integer) RetryHandler.retryUntilSuccessWithReturn(() -> {
            System.out.println("구매금액을 입력해 주세요.");
            String purchaseAmount = Console.readLine();
            validatePurchaseAmount(purchaseAmount);
            return Integer.parseInt(purchaseAmount);
        });
    }

    private static void validatePurchaseAmount(String purchaseAmount) {
        validateInteger(purchaseAmount);
        int amount = Integer.parseInt(purchaseAmount);
        validatePositiveInteger(amount);
    }

    private static void validateInteger(String purchaseAmount) {
        try {
            Integer.parseInt(purchaseAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수를 입력해주세요.");
        }
    }

    private static void validatePositiveInteger(int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException("양수가 아닙니다.");
        }
    }

    public static DrawResult inputDrawResult() {
        List<LottoNumber> winningLottoNumbers = inputWinningLottoNumbers();

        return (DrawResult) RetryHandler.retryUntilSuccessWithReturn(() -> {
            LottoNumber bonusNumber = inputBonusNumber();
            return new DrawResult(winningLottoNumbers, bonusNumber);
        });
    }

    public static List<LottoNumber> inputWinningLottoNumbers() {
        return (List<LottoNumber>) RetryHandler.retryUntilSuccessWithReturn(() -> {
                    System.out.println("지난 주 당첨 번호를 입력해 주세요.");
                    String winningLottoTicket = Console.readLine();
                    return Arrays.stream(winningLottoTicket.split(",", -1))
                            .map(String::strip)
                            .mapToInt(Integer::parseInt)
                            .boxed()
                            .map(LottoNumber::new)
                            .toList();
                }
        );
    }

    public static LottoNumber inputBonusNumber() {
        return (LottoNumber) RetryHandler.retryUntilSuccessWithReturn(() -> {
            System.out.println("보너스 볼을 입력해 주세요.");
            String input = Console.readLine();
            validateInteger(input);
            return new LottoNumber(Integer.parseInt(input));
        });
    }
}
