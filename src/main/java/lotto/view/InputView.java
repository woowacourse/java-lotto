package lotto.view;

import lotto.model.customer.CustomLottoNumbers;
import lotto.model.customer.PurchaseAmount;
import lotto.model.lotto.BonusNumber;
import lotto.model.lotto.LottoNumber;
import lotto.model.lotto.LottoTicket;
import lotto.model.winninglotto.WinningLotto;
import lotto.utils.StringUtility;

import java.util.*;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static PurchaseAmount inputPurchaseAmount() {
        try {
            System.out.println("구입 금액을 입력해주세요.");
            return new PurchaseAmount(Integer.parseInt(SCANNER.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("잘못된 금액입니다.");
            return inputPurchaseAmount();
        }
    }

    public static int inputManualPurchaseQuantity() {
        try {
            System.out.println("수동으로 구매할 로또 수를 입력해주세요.");
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("잘못된 구매 개수 입니다.");
            return inputManualPurchaseQuantity();
        }
    }

    public static WinningLotto inputWinningLotto() {
        try {
            System.out.println("지난 주 당첨 번호를 입력해주세요.");
            Set<LottoNumber> winningLotto = StringUtility.parseIntList(SCANNER.nextLine(), ",")
                    .stream()
                    .map(LottoNumber::new)
                    .collect(Collectors.toCollection(TreeSet::new));
            return new WinningLotto(new LottoTicket(winningLotto), inputBonusNumber());
        } catch (NumberFormatException e) {
            System.out.println("잘못된 번호입니다.");
            return inputWinningLotto();
        }
    }

    public static BonusNumber inputBonusNumber() {
        try {
            System.out.println("보너스 볼을 입력해주세요.");
            return new BonusNumber(Integer.parseInt(SCANNER.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("잘못된 번호입니다.");
            return inputBonusNumber();
        }
    }

    public static CustomLottoNumbers inputCustomLottoNumbers(int purchaseQuantity) {
        if (purchaseQuantity > 0) {
            System.out.println("수동으로 구매할 번호를 입력해주세요.");
        }
        try {
            return new CustomLottoNumbers(addCustomLottoNumbers(purchaseQuantity));
        } catch (NumberFormatException e) {
            System.out.println("잘못된 번호입니다.");
            return inputCustomLottoNumbers(purchaseQuantity);
        }
    }

    private static List<List<Integer>> addCustomLottoNumbers(int purchaseQuantity) {
        List<List<Integer>> customLottoNumbers = new ArrayList<>();
        for (int i = 0; i < purchaseQuantity; i++) {
            customLottoNumbers.add(inputCustomLottoNumber());
        }
        return customLottoNumbers;
    }

    private static List<Integer> inputCustomLottoNumber() {
        List<Integer> customLottoNumber = StringUtility.parseIntList(SCANNER.nextLine(), ",");

        Collections.sort(customLottoNumber);
        return customLottoNumber;
    }
}
