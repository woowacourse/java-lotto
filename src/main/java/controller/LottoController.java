package controller;

import static java.util.stream.Collectors.toList;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoNumberRepository;
import domain.LottoResult;
import domain.Lottos;
import domain.Wallet;
import domain.WinningLotto;
import domain.service.LottoService;
import java.util.List;
import view.InputView;
import view.ResultView;

public class LottoController {
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
        InputView.printToInformManualLottoInput();
        Lottos manualLottos = manualLottoService.createLottosByQuantity(wallet.getManualQuantity());
        if (wallet.hasNoAutoQuantity()) {
            return manualLottos;
        }

        Lottos autoLottos = autoLottoService.createLottosByQuantity(wallet.getAutoQuantity());

        return manualLottos.merge(autoLottos);
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
        try {
            List<Integer> winningNumberValues = InputView.scanWinningNumbers();

            List<LottoNumber> lottoNumbers = winningNumberValues.stream()
                    .map(LottoNumberRepository::getLottoNumberByInt)
                    .collect(toList());
            return new Lotto(lottoNumbers);
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            return getLottoForWinningLotto();
        }
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
