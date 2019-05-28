package lotto.view;

import lotto.model.CustomLottoNumbers;
import lotto.model.PurchaseAmount;

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

    public static int inputPurchaseQuantity(){
        try {
            System.out.println("수동으로 구매할 로또 수를 입력해주세요..");
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("잘못된 구매 개수 입니다.");
            return inputPurchaseQuantity();
        }
    }

    public static CustomLottoNumbers inputCustomLottoNumbers(int purchaseQuantity) {
        try {
            System.out.println("수동으로 구매할 번호를 입력해주세요.");
            return new CustomLottoNumbers(addCustomLottoNumbers(purchaseQuantity));
        } catch (NumberFormatException e) {
            System.out.println("잘못된 번호입니다.");
            return inputCustomLottoNumbers(purchaseQuantity);
        }
    }

    private static List<List<Integer>> addCustomLottoNumbers(int purchaseQuantity){
        List<List<Integer>> customLottoNumbers = new ArrayList<>();
        for (int i = 0; i < purchaseQuantity; i++) {
            customLottoNumbers.add(inputCustomLottoNumber());
        }
        return customLottoNumbers;
    }

    private static List<Integer> inputCustomLottoNumber() {
        List<Integer> customLottoNumber = Arrays.stream(SCANNER.nextLine()
                .split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Collections.sort(customLottoNumber);
        return customLottoNumber;
    }
}
