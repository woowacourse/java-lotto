package lotto;

import lotto.domain.Lotteries;
import lotto.domain.Lotto;
import lotto.domain.LottoGenerateBase;
import lotto.domain.LottoNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
public class LottoApplication {
    public static void main(String[] args) {
        long userPrice = InputView.generateInvalidUserPrice();
        long manualCount = InputView.generateInvalidManualCount(userPrice);
        long autoCount = calculateAutoLottoCount(userPrice, manualCount);
        OutputView.outputUserBuyLottoCount(manualCount, autoCount);
        OutputView.titleInputAutoLotto();
        Lotteries manualLotteries = generateManualLotteries(manualCount);
        Lotteries autoLotteries = generateAutoLotteries(autoCount);
        OutputView.outputAutoLotteries(autoLotteries);
    }

    private static long calculateAutoLottoCount(long userPrice, long manualCount) {
        return (userPrice - manualCount * 1000) / 1000;
    }

    private static Lotteries generateManualLotteries(long manualCount) {
        Lotteries lotteries = new Lotteries();
        for (int i = 0; i < manualCount; i++) {
            lotteries.add(InputView.generateInvalidLotto());
        }
        return lotteries;
    }

    private static Lotteries generateAutoLotteries(long autoCount) {
        Lotteries lotteries = new Lotteries();
        for (int i = 0; i < autoCount; i++) {
            lotteries.add(generateLotto());
        }
        return lotteries;
    }

    private static Lotto generateLotto() {
        List<LottoNumber> lotto = new ArrayList<>();
        Collections.shuffle(LottoGenerateBase.lottoGenerateBase);
        for (int i = 0; i < 6; i++) {
            lotto.add(LottoGenerateBase.lottoGenerateBase.get(i));
        }
        return Lotto.create(lotto);
    }
}
