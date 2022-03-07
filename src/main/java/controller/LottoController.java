package controller;

import static java.util.stream.Collectors.toList;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoResult;
import domain.LottoService;
import domain.Lottos;
import domain.Wallet;
import domain.WinningLotto;
import java.util.List;
import view.InputView;
import view.ResultView;

public class LottoController {
    public static final int ZERO_FOR_EMPTY_QUANTITY = 0;

    public void start() {
        Wallet wallet = setupWallet();
        setupQuantity(wallet);

        Lottos lottos = setupLottos(wallet);
        InputView.printLottos(wallet, lottos);

        WinningLotto winningLotto = setupWinningLotto();
        ResultView.printResult(LottoResult.of(winningLotto, lottos));
    }

    private Wallet setupWallet() {
        try {
            return new Wallet(InputView.scanInputMoney());
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            return setupWallet();
        }
    }

    private void setupQuantity(Wallet wallet) {
        buyManualLotto(wallet);
        buyAutoLotto(wallet);
    }

    private void buyManualLotto(Wallet wallet) {
        try {
            int manualQuantity = InputView.scanManualLottoQuantity();
            wallet.buyManualLottoByQuantity(manualQuantity);
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            buyManualLotto(wallet);
        }
    }

    private void buyAutoLotto(Wallet wallet) {
        if (wallet.isEmpty()) {
            return;
        }

        wallet.buyAutoLottoWithCurrentBalance();
    }

    private Lottos setupLottos(Wallet wallet) {
        Lottos manualLottos = setupManualLottos(wallet);
        Lottos autoLottos = setupAutoLottos(wallet);

        return manualLottos.merge(autoLottos);
    }

    private Lottos setupManualLottos(Wallet wallet) {
        if (wallet.getManualQuantity() == ZERO_FOR_EMPTY_QUANTITY) {
            return Lottos.EMPTY_LOTTOS;
        }

        InputView.printToInformManualLottoInput();
        List<List<Integer>> scannedManualLottoNumbers = InputView.scanManualLottoNumbers(wallet.getManualQuantity());
        return LottoService.createManual(scannedManualLottoNumbers);
    }

    private Lottos setupAutoLottos(Wallet wallet) {
        if (wallet.getAutoQuantity() == ZERO_FOR_EMPTY_QUANTITY) {
            return Lottos.EMPTY_LOTTOS;
        }

        return LottoService.createAuto(wallet.getAutoQuantity());
    }

    private WinningLotto setupWinningLotto() {
        Lotto lotto = new Lotto(getLottoNumbers());
        LottoNumber bonusNumber = getLottoNumberForBonusNumber();

        try {
            return new WinningLotto(lotto, bonusNumber);
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            return setupWinningLotto();
        }
    }

    private List<LottoNumber> getLottoNumbers() {
        return InputView.getLottoNumbers()
                .stream()
                .map(LottoNumber::getInstance)
                .collect(toList());
    }

    private LottoNumber getLottoNumberForBonusNumber() {
        try {
            int bonusNumberValue = InputView.scanBonusNumber();
            return LottoNumber.getInstance(bonusNumberValue);
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            return getLottoNumberForBonusNumber();
        }
    }
}
