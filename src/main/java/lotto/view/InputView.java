package lotto.view;

import lotto.model.customer.PurchaseAmount;
import lotto.model.lotto.*;
import lotto.model.winninglotto.WinningLotto;
import lotto.utils.StringUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class InputView {
    private static final int MIN_PURCHASE_QUANTITY = 1;
    private static final String NUMBER_SPLIT_DELIMITER = ",";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static PurchaseAmount inputPurchaseAmount() {
        try {
            System.out.println("구입 금액을 입력해주세요.");
            return PurchaseAmount.from(Integer.parseInt(SCANNER.nextLine()));
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
            Set<LottoNumber> winningLotto = StringUtility.parseIntList(SCANNER.nextLine(), NUMBER_SPLIT_DELIMITER)
                    .stream()
                    .map(LottoNumberRepository::fromNumber)
                    .collect(Collectors.toCollection(TreeSet::new));
            return WinningLotto.of(LottoTicket.from(winningLotto), inputBonusNumber());
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

    public static LottoTickets inputCustomLottoTickets(int purchaseQuantity) {
        if (purchaseQuantity >= MIN_PURCHASE_QUANTITY) {
            System.out.println("수동으로 구매할 번호를 입력해주세요.");
        }
        try {
            return LottoTickets.from(addCustomLottoTickets(purchaseQuantity));
        } catch (NumberFormatException e) {
            System.out.println("잘못된 번호입니다.");
            return inputCustomLottoTickets(purchaseQuantity);
        }
    }

    private static List<LottoTicket> addCustomLottoTickets(int purchaseQuantity) {
        List<LottoTicket> CustomLottoTickets = new ArrayList<>();
        for (int i = 0; i < purchaseQuantity; i++) {
            CustomLottoTickets.add(LottoTicket.from(inputCustomLottoTicket()));
        }
        return CustomLottoTickets;
    }

    private static Set<LottoNumber> inputCustomLottoTicket() {
        List<Integer> CustomLottoTicket = StringUtility.parseIntList(SCANNER.nextLine(), NUMBER_SPLIT_DELIMITER);

        return CustomLottoTicket.stream()
                .map(LottoNumberRepository::fromNumber)
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
