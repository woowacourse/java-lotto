package controller;

import static view.util.LottoConstants.LOTTO_PRICE_PER_ONE;

import model.BonusNumber;
import model.Lotto;
import model.LottoRepository;
import model.RankType;
import model.UserLotto;
import view.InputView;
import view.OutputView;
import view.util.RandomNumberGenerator;

public class LottoController {
    public static void lottoStart() {
        LottoRepository lottoRepository = new LottoRepository();
        int userMoney = InputView.inputAndValidateUserMoney();

        for (int i = 0; i < userMoney / LOTTO_PRICE_PER_ONE; i++) {
            lottoRepository.addLotto(makeLotto());
        }
        OutputView.printBuyQuantity(userMoney / LOTTO_PRICE_PER_ONE);
        OutputView.printRandomLotto(lottoRepository);

        UserLotto userLotto = new UserLotto(InputView.inputWinningNumbers());
        BonusNumber bonusNumber = isDuplicateBonusNumber(userLotto);

        calculateResultAndPrintResult(lottoRepository, userLotto, bonusNumber);
        OutputView.printWinningRate(calculateWinningRate(userMoney));
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


    private static Lotto makeLotto() {
        try {
            return new Lotto(RandomNumberGenerator.makeRandomNumber());
        } catch (IllegalArgumentException e) {
            return makeLotto();
        }
    }

    public static void calculateResultAndPrintResult(LottoRepository lottoRepository, UserLotto userLotto, BonusNumber bonusNumber) {
        for (Lotto lotto : lottoRepository.getLottos()) {
            RankType.saveGameResult(userLotto.calculateRank(lotto), bonusNumber.isBonusNumber(lotto));
        }
        OutputView.printResult(RankType.printResult());
    }

    private static double calculateWinningRate(int userMoney){
        int totalPrice = RankType.calculateTotalPrice();
        return (double)totalPrice / userMoney;
    }


}
