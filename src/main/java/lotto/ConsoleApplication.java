package lotto;

import lotto.domain.*;
import lotto.domain.LottoNumber;
import lotto.domain.generator.ManualLottoNumbersGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

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
            LottoCount lottoCount =
                    new LottoCount(InputView.inputNumberOfManualLottos(), money);
            PurchaseInformation purchaseInformation = new PurchaseInformation(lottoCount);
            if (purchaseInformation.hasManualLottos()) {
                registerManualLottosNumbers(purchaseInformation);
            }
            return purchaseInformation;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return setUpPurchaseInformation();
        }
    }

    private static void registerManualLottosNumbers(PurchaseInformation purchaseInformation) {
        OutputView.requestManualLottosMessage();
        for (int i = 0; i < purchaseInformation.getManualLottoCount(); i++) {
            purchaseInformation.addManualLottoNumbers(InputView.inputLottoNumbers());
        }
    }

    private static Lottos purchaseLottos(PurchaseInformation purchaseInformation) {
        Lottos lottos = LottoMachine.buyLottos(purchaseInformation);

        OutputView.outputLottosPurchaseMessage(purchaseInformation);
        OutputView.outputLottos(lottos);
        return lottos;
    }

    private static LottoGame setUpLottoGame() {
        OutputView.requestWinningNumbersMessage();
        try {
            ManualLottoNumbersGenerator manualLottoNumbersGenerator = ManualLottoNumbersGenerator.getInstance();
            manualLottoNumbersGenerator.register(InputView.inputLottoNumbers());
            LottoNumbers winningNumbers = manualLottoNumbersGenerator.generate();
            LottoNumber bonusNumber = LottoNumber.valueOf(InputView.inputBonusBall());
            WinningInformation winningInformation = new WinningInformation(winningNumbers, bonusNumber);
            return new LottoGame(winningInformation);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return setUpLottoGame();
        }
    }
}
