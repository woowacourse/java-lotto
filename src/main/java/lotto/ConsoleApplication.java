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
            LottoCount lottoCount = new LottoCount(InputView.inputNumberOfManualLottos(), money);
            return new PurchaseInformation(lottoCount);
        } catch (NumberFormatException e) {
            System.err.println("잘못된 입력입니다.");
            return setUpPurchaseInformation();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return setUpPurchaseInformation();
        }
    }

    private static Lottos purchaseLottos(PurchaseInformation purchaseInformation) {
        try {
            registerManualLottosNumbers(purchaseInformation);

            Lottos lottos = LottoMachine.buyLottos(purchaseInformation);

            OutputView.outputLottosPurchaseMessage(purchaseInformation);
            OutputView.outputLottos(lottos);
            return lottos;
        } catch (NumberFormatException e) {
            System.err.println("잘못된 입력입니다.");
            return purchaseLottos(purchaseInformation);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return purchaseLottos(purchaseInformation);
        }
    }

    private static void registerManualLottosNumbers(PurchaseInformation purchaseInformation) {
        if (!purchaseInformation.hasManualLottos()) {
            return;
        }

        OutputView.requestManualLottosMessage();
        for (int i = 0; i < purchaseInformation.getManualLottoCount(); i++) {
            purchaseInformation.addManualLottoNumbers(InputView.inputLottoNumbers());
        }
    }

    private static LottoGame setUpLottoGame() {
        try {
            OutputView.requestWinningNumbersMessage();

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
