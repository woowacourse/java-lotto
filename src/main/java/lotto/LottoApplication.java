package lotto;

import lotto.controller.LottoService;
import lotto.domain.InputNegativeException;
import lotto.domain.WinningResult;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNo;
import lotto.utils.LottoNoParser;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoApplication {
    private static LottoService service = new LottoService();

    public static void main(String[] args) {
        makeBudget();

        int countOfManualLotto = getCountOfManualLotto();
        if (countOfManualLotto != 0) {
            OutputView.printManualInputMsg();
        }
        service.makeManualLotto(makeManualLottos(countOfManualLotto));
        service.makeAutoLotto();
        OutputView.printContainingLottos(service.createLottoDtos());

        makeWinningLotto();

        WinningResult winningResult = service.checkWinningLotto();
        OutputView.printResult(winningResult);
    }

    private static boolean makeBudget() {
        try {
            service.makeBuyer(InputView.inputBudget());
            return true;
        } catch (Exception e) {
            OutputView.printErrorMsg(e);
        }
        return makeBudget();
    }

    private static int getCountOfManualLotto() {
        try {
            int countOfManualLotto = InputView.inputCountOfManualLotto();
            validatePositive(countOfManualLotto);
            service.validataeAffordability(countOfManualLotto);
            return countOfManualLotto;
        } catch (Exception e) {
            OutputView.printErrorMsg(e);
            return getCountOfManualLotto();
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

    private static boolean makeWinningLotto() {
        try {
            List<LottoNo> winningLottoNo = LottoNoParser.parseToLottoNos(InputView.inputWinningLotto());
            return service.makeWinningLotto(winningLottoNo, new LottoNo(InputView.inputBonusNo()));
        } catch (Exception e) {
            OutputView.printErrorMsg(e);
            return makeWinningLotto();
        }
    }
}