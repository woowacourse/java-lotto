package controller;

import static domain.LottoResultHandler.getLottoResultDto;
import static java.util.stream.Collectors.toList;

import domain.Lotto;
import domain.LottoFactory;
import domain.LottoNumber;
import domain.LottoNumberRepository;
import domain.Lottos;
import domain.WinningLotto;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.ResultView;
import vo.Wallet;

public class LottoController {
    private static final int NO_AUTO_QUANTITY = 0;

    public void start() {
        Wallet wallet = setupWallet();
        setupQuantity(wallet);

        Lottos lottos = setupLottos(wallet);
        InputView.printLottos(wallet, lottos);

        WinningLotto winningLotto = setupWinningLotto();
        ResultView.printResult(getLottoResultDto(winningLotto, lottos));
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
        buyAuttoLotto(wallet);
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

    private void buyAuttoLotto(Wallet wallet) {
        if (wallet.isEmpty()) {
            return;
        }

        wallet.buyAutoLottoWithCurrentBalance();
    }

    private Lottos setupLottos(Wallet wallet) {
        Lottos manualLottos = setupManualLottos(wallet.getManualQuantity());
        if (wallet.getAutoQuantity() == NO_AUTO_QUANTITY) {
            return manualLottos;
        }

        Lottos autoLottos = LottoFactory.createAutoLottosByQuantity(wallet.getAutoQuantity());

        return manualLottos.merge(autoLottos);
    }

    private Lottos setupManualLottos(int manualQuantity) {
        InputView.printToInformManualLottoInput();

        List<Lotto> lottos = new ArrayList<>();
        do {
            lottos.add(getManualLotto());
        } while (lottos.size() < manualQuantity);

        return new Lottos(lottos);
    }

    private Lotto getManualLotto() {
        try {
            List<Integer> lists = InputView.scanManualLottoNumbers();
            return new Lotto(getLottoNumbers(lists));
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            return getManualLotto();
        }
    }

    private List<LottoNumber> getLottoNumbers(List<Integer> lottoNumbers) {
        return lottoNumbers.stream()
                .sorted()
                .map(LottoNumberRepository::getLottoNumberByInt)
                .collect(toList());
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
}
