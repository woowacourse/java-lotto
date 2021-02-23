package lotto;

import lotto.domain.*;
import lotto.exception.LottoException;
import lotto.util.LottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoManager {

    public static LottoGroup createLotto() {
        LottoGroup lottoGroup = createLottoGroup(InputView.getMoney());
        OutputView.printBoughtLotto(lottoGroup);
        return lottoGroup;
    }

    public static WinningLotto getWinningLotto() {
        try {
            return new WinningLotto(InputView.getWinningNumbers(), InputView.getBonusNumber());
        } catch (LottoException e) {
            OutputView.printMessage(e.getMessage());
            return getWinningLotto();
        }
    }

    public static void printResult(LottoGroup lottoGroup, WinningLotto winningLotto) {
        LottoResult lottoResult = new LottoResult();
        lottoGroup.lottoGroup()
                .stream()
                .map(winningLotto::matchRank)
                .forEach(lottoResult::add);
        OutputView.printLottoResult(lottoResult);
    }

    private static LottoGroup createLottoGroup(Money money) {
        try {
            LottoSeller lottoSeller = new LottoSeller();
            int count = InputView.getManualLottoCount();
            if (count != 0) {
                List<Lotto> lottos = createManualLotto(InputView.getManualNumbers(count));
                return lottoSeller.sellManualLotto(money, count, lottos);
            }
            return lottoSeller.sellLotto(money);
        } catch (LottoException e) {
            OutputView.printMessage(e.getMessage());
            return createLottoGroup(InputView.getMoney());
        } catch (NumberFormatException e) {
            OutputView.printMessage("숫자를 입력해주세요.");
            return createLottoGroup(InputView.getMoney());
        }
    }

    private static List<Lotto> createManualLotto(List<String> numbers) {
        List<Lotto> lottos = new ArrayList<>();
        for (String number : numbers) {
            lottos.add(LottoGenerator.generate(Arrays
                    .stream(number.split(","))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList())));
        }
        return lottos;
    }
}
