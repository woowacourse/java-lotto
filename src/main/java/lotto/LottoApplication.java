package lotto;

import lotto.domain.*;
import lotto.utils.LottoNoParser;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoApplication {
    public static void main(String[] args) {
        LottoBuyer buyer = makeBuyer();

        int countOfManualLotto = getCountOfManualLotto(buyer);
        if (countOfManualLotto != 0) {
            OutputView.printManualInputMsg();
        }
        buyer.buyManualLotto(makeManualLottos(countOfManualLotto));
        buyer.buyAutoLotto();
        OutputView.printContainingLottos(buyer);

        WinningLotto winningLotto = makeWinningLotto();

        WinningResult winningResult = buyer.checkWinningLotto(winningLotto);
        OutputView.printResult(winningResult);
    }

    private static LottoBuyer makeBuyer() {
        try {
            return new LottoBuyer(new Budget(InputView.inputBudget()));
        } catch (NoMoneyException nme) {
            OutputView.printErrorMsg(nme);
            System.exit(0);
            return null;
        } catch (Exception e) {
            OutputView.printErrorMsg(e);
            return makeBuyer();
        }
    }

    private static int getCountOfManualLotto(LottoBuyer buyer) {
        try {
            int countOfManualLotto = InputView.inputCountOfManualLotto();
            validatePositive(countOfManualLotto);
            buyer.validateAffordability(countOfManualLotto);
            return countOfManualLotto;
        } catch (Exception e) {
            OutputView.printErrorMsg(e);
            return getCountOfManualLotto(buyer);
        }
    }

    private static void validatePositive(int countOfManualLotto) {
        if (countOfManualLotto < 0) {
            throw new InputNegativeException("음수를 입력할 수 없습니다.");
        }
    }

    private static List<Lotto> makeManualLottos(int countOfManualLotto) {
        List<Lotto> lottos = new ArrayList<>();
        while (lottos.size() < countOfManualLotto) {
            addManualLotto(lottos);
        }
        return lottos;
    }

    private static void addManualLotto(List<Lotto> lottos) {
        try {
            lottos.add(Lotto.of(LottoNoParser.parseToLottoNos(InputView.inputManualLotto())));
        } catch (Exception e) {
            OutputView.printErrorMsg(e);
            addManualLotto(lottos);
        }
    }

    private static WinningLotto makeWinningLotto() {
        try {
            List<LottoNo> winningLottoNo = LottoNoParser.parseToLottoNos(InputView.inputWinningLotto());
            return WinningLotto.of(winningLottoNo, new LottoNo(InputView.inputBonusNo()));
        } catch (Exception e) {
            OutputView.printErrorMsg(e);
            return makeWinningLotto();
        }
    }
}