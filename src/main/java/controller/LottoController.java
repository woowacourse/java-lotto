package controller;

import static constant.LottoConstants.LOTTO_RANGE_MAX;
import static constant.LottoConstants.LOTTO_RANGE_MIN;

import constant.LottoConstants;
import constant.WinningCount;
import domain.Lottos;
import domain.WinningLotto;
import dto.IssuedLottosDto;
import java.util.List;
import java.util.Map;
import java.util.Random;
import util.LottoResultCalculator;
import view.InputView;
import view.OutputView;

public class LottoController {

    public void start() {
        Lottos purchasedLottos = issueLotto();
        IssuedLottosDto issuedLottosDto = IssuedLottosDto.from(purchasedLottos);
        OutputView.printLottoReceipt(issuedLottosDto);
        WinningLotto winningLotto = makeWinningLotto();
        Map<WinningCount, Integer> result = winningLotto.getLottosResult(purchasedLottos);
        Double earningRate = LottoResultCalculator.calculateEarningRate(result,
                issuedLottosDto.lottos().size() * LottoConstants.LOTTO_PRICE.getValue());
        OutputView.printLottoResult(result, earningRate);
    }

    private Lottos issueLotto() {
        int money = InputView.askMoney();
        return new Lottos(money,
                () -> {
                    Random random = new Random();
                    return random.nextInt(LOTTO_RANGE_MAX.getValue() - LOTTO_RANGE_MIN.getValue() + 1)
                            + LOTTO_RANGE_MIN.getValue();
                });
    }

    private WinningLotto makeWinningLotto() {
        List<Integer> numbers = InputView.askWinningLotto();
        Integer bonusNumber = InputView.askBonusNumber();
        return new WinningLotto(numbers, bonusNumber);
    }

}
