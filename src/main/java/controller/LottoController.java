package controller;

import java.util.Random;
import model.BonusNumber;
import model.Lotto;
import model.LottoRepository;
import model.UserLotto;
import view.InputView;
import view.OutputView;
import view.util.RandomNumberGenerator;

public class LottoController {
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

    public static void lottoStart() {
        LottoRepository lottoRepository = new LottoRepository();
        int userMoney = InputView.inputAndValidateUserMoney();
        for (int i = 0; i < userMoney / 1000; i++) {
            lottoRepository.addLotto(makeLotto());
        }
        OutputView.printBuyQuantity(userMoney / 1000);
        OutputView.printRandomLotto(lottoRepository);

        UserLotto userLotto = new UserLotto(InputView.inputWinningNumbers());
        isDuplicateBonusNumber(userLotto);


    }

    private static Lotto makeLotto() {
        try {
            return new Lotto(RandomNumberGenerator.makeRandomNumber());
        } catch (IllegalArgumentException e) {
            return makeLotto();
        }
    }

}
