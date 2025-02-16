package controller;

import model.BonusNumber;
import model.Lotto;
import model.LottoRepository;
import model.UserLotto;
import model.Wallet;
import model.WinningStatistics;
import view.InputView;
import view.OutputView;
import view.util.RandomNumberGenerator;

public class LottoController {
    public static void lottoStart() {
        LottoRepository lottoRepository = new LottoRepository();

        Wallet wallet = createWallet();

        buyLottoForUserMoney(lottoRepository, wallet.getPurchasableQuantity());

        OutputView.printBuyQuantity(wallet.getPurchasableQuantity());
        OutputView.printRandomLotto(lottoRepository);

        UserLotto userLotto = createUserLotto();
        BonusNumber bonusNumber = isDuplicateBonusNumber(userLotto);

        calculateResultAndPrintResult(wallet, lottoRepository, userLotto, bonusNumber);
    }

    private static Wallet createWallet() {
        try {
            return new Wallet(InputView.inputAndValidateUserMoney());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createWallet();
        }
    }

    private static UserLotto createUserLotto() {
        try {
            return new UserLotto(InputView.inputWinningNumbers());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createUserLotto();
        }
    }

    private static void buyLottoForUserMoney(LottoRepository lottoRepository, int quantity) {
        for (int i = 0; i < quantity; i++) {
            lottoRepository.addLotto(new Lotto(RandomNumberGenerator.makeRandomNumber()));
        }
    }


    public static BonusNumber isDuplicateBonusNumber(UserLotto userLotto) {
        try {
            BonusNumber bonusNumber = new BonusNumber(InputView.isNumericBonusNumber());
            userLotto.isDuplicateBonusNumber(bonusNumber.getBonusNumber());
            return bonusNumber;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return isDuplicateBonusNumber(userLotto);
        }
    }

    public static void calculateResultAndPrintResult(Wallet wallet, LottoRepository lottoRepository,
                                                     UserLotto userLotto, BonusNumber bonusNumber) {
        WinningStatistics winningStatistics = new WinningStatistics(lottoRepository, userLotto, bonusNumber);

        OutputView.printResult(winningStatistics);

        OutputView.printWinningRate(winningStatistics.calculateWinningRate(wallet));
    }


}
