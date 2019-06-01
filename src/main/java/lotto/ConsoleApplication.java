package lotto;

import lotto.Utils.NumbersSplitter;
import lotto.domain.*;
import lotto.domain.LottoNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class ConsoleApplication {
    public static void main(String[] args) {
        PurchaseInformation purchaseInformation = setUpPurchaseInformation();

        Lottos lottos = purchaseLottos(purchaseInformation);

        LottoGame lottoGame = setUpLottoGame();

        LottoResult lottoResult = lottoGame.play(lottos);
        OutputView.outputResult(lottoResult);
    }

    private static PurchaseInformation setUpPurchaseInformation() {
        try {
            Money money = new Money(InputView.inputMoney());
            return new PurchaseInformation(money, InputView.inputNumberOfManualLottos());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return setUpPurchaseInformation();
        }
    }

    private static Lottos purchaseLottos(PurchaseInformation purchaseInformation) {
        int numberOfManualLottos = purchaseInformation.getNumberOfManualLottos();
        int numberOfAutoLottos = purchaseInformation.getNumberOfAutoLottos();

        Lottos lottos = LottoMachine.buyManualLotto(makeManualLottosNumbers(numberOfManualLottos));
        lottos.addAll(LottoMachine.buyAutoLottos(numberOfAutoLottos));

        OutputView.outputLottosPurchaseMessage(numberOfManualLottos, numberOfAutoLottos);
        OutputView.outputLottos(lottos);
        return lottos;
    }

    private static List<LottoNumbers> makeManualLottosNumbers(int numberOfManualLottos) {
        OutputView.requestManualLottosMessage();
        List<LottoNumbers> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < numberOfManualLottos; i++) {
            lottoNumbers.add(makeLottoNumbers());
        }
        return lottoNumbers;
    }

    private static LottoGame setUpLottoGame() {
        OutputView.requestWinningNumbersMessage();

        try {
            LottoNumbers winningNumbers = makeLottoNumbers();
            LottoNumber bonusNumber = LottoNumbersGenerator.getLottoNumber(InputView.inputBonusBall());
            WinningInformation winningInformation = new WinningInformation(winningNumbers, bonusNumber);
            return new LottoGame(winningInformation);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return setUpLottoGame();
        }
    }

    private static LottoNumbers makeLottoNumbers() {
        try {
            List<Integer> inputNumbers = NumbersSplitter.splitNumbers(InputView.inputLottoNumbers());
            return LottoNumbersGenerator.getLottoNumbers(inputNumbers);
        } catch (NumberFormatException e) {
            System.err.println("올바른 수를 입력해 주세요.");
            return makeLottoNumbers();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return makeLottoNumbers();
        }
    }
}
