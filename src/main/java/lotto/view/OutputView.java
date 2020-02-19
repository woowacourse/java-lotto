package lotto.view;

import lotto.domain.AllLottoNumbers;
import lotto.domain.LottoNumberGroup;
import lotto.domain.LottoNumbers;
import lotto.domain.PurchaseNumber;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String PURCHASE_NUMBER_POSTFIX = "개를 구입했습니다.";

    public static void printPurchaseNumber(PurchaseNumber purchaseNumber) {
        System.out.println(purchaseNumber.getPurchaseNumber() + PURCHASE_NUMBER_POSTFIX);
    }

    // TODO : 일급 컬렉션의 책임이 어느정도인지 의견을 물어봅시다!
    public static void printAllLottoNumbers(AllLottoNumbers allLottoNumbers) {
        List<LottoNumbers> allLottoNumbersList = allLottoNumbers.getAllLottoNumbers();
        for (int i = 0; i < allLottoNumbersList.size(); i++) {
            printLottoNumbers(allLottoNumbersList.get(i));
        }
    }

    private static void printLottoNumbers(LottoNumbers lottoNumbers) {
        List<LottoNumberGroup> lottoNumberGroups = lottoNumbers.getLottoNumbers();
        String output = lottoNumberGroups.stream()
                .map(LottoNumberGroup::getValue)
                .map(Object::toString)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(output);
    }
}
