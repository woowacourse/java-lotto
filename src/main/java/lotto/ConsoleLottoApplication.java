package lotto;

import lotto.domain.number.AllLottoNumbers;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.LottoNumbers;
import lotto.domain.number.PurchaseNumber;
import lotto.domain.random.RandomNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class ConsoleLottoApplication {
    private static RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    public static void main(String[] args) {
        PurchaseNumber purchaseNumber = InputView.inputPurchaseMoney();
        OutputView.printPurchaseNumber(purchaseNumber);
        List<LottoNumbers> lottoNumbersList = new ArrayList<>();
        for (int i = 0; i < purchaseNumber.getPurchaseNumber(); i++) {
            List<LottoNumber> randomNumbers = randomNumberGenerator.generateNumbers();
            lottoNumbersList.add(new LottoNumbers(randomNumbers));
        }
        AllLottoNumbers allLottoNumbers = new AllLottoNumbers(lottoNumbersList);
    }
}
