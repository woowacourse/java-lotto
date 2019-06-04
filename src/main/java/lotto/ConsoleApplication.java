package lotto;

import lotto.domain.*;
import lotto.domain.LottoNumber;
import lotto.domain.generator.LottoNumbersGenerator;
import lotto.domain.generator.ManualLottoNumbersGenerator;
import lotto.utils.NumbersSplitter;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleApplication {
    public static void main(String[] args) {
        try {
            PurchaseInformation purchaseInformation = setUpPurchaseInformation();

            Lottos lottos = purchaseLottos(purchaseInformation);

            LottoGame lottoGame = setUpLottoGame();

            LottoResult lottoResult = lottoGame.play(lottos);
            OutputView.outputResult(lottoResult);
        } catch (NumberFormatException e) {
            System.err.println("잘못된 수가 입력되었습니다.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static PurchaseInformation setUpPurchaseInformation() {
        Money money = new Money(InputView.inputMoney());
        LottoCount lottoCount = new LottoCount(InputView.inputNumberOfManualLottos(), money);
        return new PurchaseInformation(lottoCount);
    }

    private static Lottos purchaseLottos(PurchaseInformation purchaseInformation) {
        registerManualLottosNumbers(purchaseInformation);

        Lottos lottos = LottoMachine.buyLottos(purchaseInformation);

        OutputView.outputLottosPurchaseMessage(purchaseInformation);
        OutputView.outputLottos(lottos);
        return lottos;
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
        OutputView.requestWinningNumbersMessage();

        LottoNumbersGenerator manualLottoNumbersGenerator =
                ManualLottoNumbersGenerator.getInstance(NumbersSplitter.split(InputView.inputLottoNumbers()));
        LottoNumbers winningNumbers = manualLottoNumbersGenerator.generate();

        LottoNumber bonusNumber = LottoNumber.valueOf(InputView.inputBonusBall());

        WinningInformation winningInformation = new WinningInformation(winningNumbers, bonusNumber);
        return new LottoGame(winningInformation);
    }
}
