package view;

import model.Lotto;
import model.LottoPurchaseAmount;
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

    public static LottoPurchaseAmount inputAmountOfManualPicks(Money investment) {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        return new TryUntilSuccess<>(() -> {
            final int amount = Integer.parseInt(input.nextLine());
            return new LottoPurchaseAmount(investment, amount);
        }).get();
    }

    public static List<Lotto> inputManualLottoNumbers(LottoPurchaseAmount purchaseAmount) {
        if (purchaseAmount.manual() == 0) {
            return new ArrayList<>();
        }
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요(쉼표로 구분).");
        return Collections.unmodifiableList(
                IntStream.range(0, purchaseAmount.manual()).boxed()
                        .map(i -> new TryUntilSuccess<>(() -> new Lotto(input.nextLine())).get())
                        .collect(Collectors.toList())
        );
    }
}