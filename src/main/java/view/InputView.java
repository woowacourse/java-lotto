package view;

import model.Lotto;
import model.LottoNumber;
import model.Money;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {
    private static final String WRONG_INPUT_MESSAGE = "잘못된 입력입니다. 다시 입력해주세요.";
    private static final Scanner input = new Scanner(System.in);

    public static Money inputAmountOfMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        try {
            return new Money(input.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(WRONG_INPUT_MESSAGE);
            return inputAmountOfMoney();
        }
    }

    public static int inputAmountOfManualPicks(int maxAmount) {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        try {
            final int amount = Integer.parseInt(input.nextLine());
            if (amount > maxAmount || amount < 0) {
                throw new IllegalArgumentException();
            }
            return amount;
        } catch (IllegalArgumentException e) {
            System.out.println(WRONG_INPUT_MESSAGE);
            return inputAmountOfManualPicks(maxAmount);
        }
    }

    public static List<Lotto> inputManualLottoNumbers(int manualPurchaseAmount) {
        if (manualPurchaseAmount == 0) {
            return new ArrayList<>();
        }
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요(쉼표로 구분).");
        return inputEachLottoNumbers(new ArrayList<>(), manualPurchaseAmount);
    }

    private static List<Lotto> inputEachLottoNumbers(List<Lotto> result, int purchaseAmount) {
        if (purchaseAmount == 0) {
            return Collections.unmodifiableList(result);
        }
        try {
            result.add(new Lotto(input.nextLine()));
            return inputEachLottoNumbers(result, --purchaseAmount);
        } catch (IllegalArgumentException e) {
            System.out.println(WRONG_INPUT_MESSAGE);
            return inputEachLottoNumbers(result, purchaseAmount);
        }
    }

    public static Set<LottoNumber> inputWinningNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        try {
            Set<LottoNumber> numbers = Stream
                    .of(input.nextLine().split(","))
                    .filter(x -> x.trim().length() != 0)
                    .map(i -> LottoNumber.of(i))
                    .collect(Collectors.toSet());
            if (numbers.size() != Lotto.NUMBER_OF_NUMBERS) {
                throw new IllegalArgumentException();
            }
            return numbers;
        } catch (IllegalArgumentException e) {
            System.out.println(WRONG_INPUT_MESSAGE);
            return inputWinningNumbers();
        }
    }

    public static LottoNumber inputBonusNumber(Set<LottoNumber> winningNumbers) {
        System.out.println("보너스 볼을 입력해 주세요.");
        try {
            LottoNumber bonusNumber = LottoNumber.of(input.nextLine());
            if (winningNumbers.contains(bonusNumber)) {
                throw new IllegalArgumentException();
            }
            return bonusNumber;
        } catch (IllegalArgumentException e) {
            System.out.println(WRONG_INPUT_MESSAGE);
            return inputBonusNumber(winningNumbers);
        }
    }
}