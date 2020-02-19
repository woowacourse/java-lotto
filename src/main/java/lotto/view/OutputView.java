package lotto.view;

import lotto.domain.number.AllLottoNumbers;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.LottoNumbers;
import lotto.domain.number.PurchaseNumber;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String PURCHASE_NUMBER_POSTFIX = "개를 구입했습니다.";

    public static void printPurchaseNumber(PurchaseNumber purchaseNumber) {
        System.out.println(purchaseNumber.getPurchaseNumber() + PURCHASE_NUMBER_POSTFIX);
    }

    public static void printAllLottoNumbers(AllLottoNumbers allLottoNumbers) {
        List<LottoNumbers> allLottoNumbersList = allLottoNumbers.getAllLottoNumbers();
        for (int i = 0; i < allLottoNumbersList.size(); i++) {
            printLottoNumbers(allLottoNumbersList.get(i));
        }
    }

    private static void printLottoNumbers(LottoNumbers lottoNumbers) {
        List<LottoNumber> lottoNumberGroups = lottoNumbers.getLottoNumbers();
        String output = lottoNumberGroups.stream()
                .map(LottoNumber::getNumber)
                .map(Object::toString)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(output);
    }
}
