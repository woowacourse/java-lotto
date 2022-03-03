package controller;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoNumberRepository;
import domain.LottoResult;
import domain.Lottos;
import domain.Wallet;
import domain.WinningLotto;
import domain.service.LottoService;
import view.InputView;
import view.ResultView;

public class LottoController {
    public static final int ZERO_FOR_EMPTY_QUANTITY = 0;

    private final LottoService manualLottoService;
    private final LottoService autoLottoService;

    public LottoController(LottoService manualLottoService, LottoService autoLottoService) {
        this.manualLottoService = manualLottoService;
        this.autoLottoService = autoLottoService;
    }

    public void start() {
        Wallet wallet = setupWallet();
        setupQuantity(wallet);

        Lottos lottos = setupLottos(wallet);
        InputView.printLottos(wallet, lottos);

        WinningLotto winningLotto = setupWinningLotto();
        ResultView.printResult(getLottoResult(winningLotto, lottos));
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
        return manualLottoService.createLottosByQuantity(wallet.getManualQuantity());
    }

    private Lottos setupAutoLottos(Wallet wallet) {
        if (wallet.getAutoQuantity() == ZERO_FOR_EMPTY_QUANTITY) {
            return Lottos.EMPTY_LOTTOS;
        }

        return autoLottoService.createLottosByQuantity(wallet.getAutoQuantity());
    }

    private WinningLotto setupWinningLotto() {
        Lotto lotto = getLottoForWinningLotto();
        LottoNumber bonusNumber = getLottoNumberForBonusNumber();

        try {
            return new WinningLotto(lotto, bonusNumber);
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            return setupWinningLotto();
        }
    }

    private Lotto getLottoForWinningLotto() {
        InputView.printMessageToScanLottoNumbers();
        return new Lotto(manualLottoService.getLottoNumbers());
    }

    private LottoNumber getLottoNumberForBonusNumber() {
        try {
            int bonusNumberValue = InputView.scanBonusNumber();
            return LottoNumberRepository.getLottoNumberByInt(bonusNumberValue);
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            return getLottoNumberForBonusNumber();
        }
    }

    private LottoResult getLottoResult(WinningLotto winningLotto, Lottos lottos) {
        return LottoResult.builder()
                .winningLotto(winningLotto)
                .lottos(lottos)
                .build();
    }
}
