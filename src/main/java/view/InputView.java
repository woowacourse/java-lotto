package view;

import model.Lotto;
import model.Money;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {
    private static final Scanner input = new Scanner(System.in);

    public static Money inputAmountOfMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return new TryUntilSuccess<>(() -> new Money(input.nextLine())).get();
    }

    public static int inputAmountOfManualPicks(int maxAmount) {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        return new TryUntilSuccess<>(() -> {
            final int amount = Integer.parseInt(input.nextLine());
            validateRange(amount, 0, maxAmount);
            return amount;
        }).get();
    }

    private static void validateRange(int target, int min, int max) {
        if (target < min || target > max) {
            throw new IllegalArgumentException();
        }
    }

    public static List<Lotto> inputManualLottoNumbers(int manualPurchaseAmount) {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요(쉼표로 구분).");
        return Collections.unmodifiableList(
                IntStream.range(0, manualPurchaseAmount).boxed()
                        .map(i -> new TryUntilSuccess<>(() -> new Lotto(input.nextLine())).get())
                        .collect(Collectors.toList())
        );
    }
}