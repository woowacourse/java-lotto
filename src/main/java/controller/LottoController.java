package controller;

import static view.util.LottoConstants.LOTTO_PRICE_PER_ONE;

import java.util.Map;
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

        buyLottoForUserMoney(lottoRepository, userMoney);

        OutputView.printBuyQuantity(userMoney / LOTTO_PRICE_PER_ONE);
        OutputView.printRandomLotto(lottoRepository);

        UserLotto userLotto = new UserLotto(InputView.inputWinningNumbers());
        BonusNumber bonusNumber = isDuplicateBonusNumber(userLotto);

        calculateResultAndPrintResult(userMoney, lottoRepository, userLotto, bonusNumber);

    }

    private static void buyLottoForUserMoney(LottoRepository lottoRepository, int userMoney) {
        for (int i = 0; i < userMoney / LOTTO_PRICE_PER_ONE; i++) {
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

    public static void calculateResultAndPrintResult(int userMoney, LottoRepository lottoRepository, UserLotto userLotto, BonusNumber bonusNumber) {
        Map<RankType, Integer> rankTypeMap = RankType.makeMap();

        for (Lotto lotto : lottoRepository.getLottos()) {
            RankType.updateMapByWinningCount(rankTypeMap, userLotto.calculateRank(lotto), bonusNumber.isBonusNumber(lotto));
        }

        OutputView.printResult(RankType.makeLottoResult(rankTypeMap));

        OutputView.printWinningRate(calculateWinningRate(userMoney,rankTypeMap));
    }

    private static double calculateWinningRate(int userMoney, Map<RankType, Integer> map){
        int totalPrice = RankType.calculateTotalPrice(map);
        return (double)totalPrice / userMoney;
    }


}
