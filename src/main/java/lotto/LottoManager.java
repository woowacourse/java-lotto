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
        Money money = InputView.getMoney();
        int manualCount = InputView.getManualLottoCount(money);
        LottoGroup lottoGroup = createLottoGroup(money, manualCount);
        OutputView.printBoughtLotto(lottoGroup, manualCount);
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

    private static LottoGroup createLottoGroup(Money money, int manualCount) {
        try {
            return LottoSeller.sellLotto(money, manualCount, createManualLotto(InputView.getManualNumbers(manualCount)));
        } catch (LottoException e) {
            OutputView.printMessage(e.getMessage());
            return createLottoGroup(InputView.getMoney(), manualCount);
        } catch (NumberFormatException e) {
            OutputView.printMessage("숫자를 입력해주세요.");
            return createLottoGroup(InputView.getMoney(), manualCount);
        }
    }

    private static List<Lotto> createManualLotto(List<String> numbers) {
        List<Lotto> lottos = new ArrayList<>();
        for (String number : numbers) {
            lottos.add(LottoGenerator.generate(Arrays
                    .stream(number.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList())));
        }
        return lottos;
    }
}
