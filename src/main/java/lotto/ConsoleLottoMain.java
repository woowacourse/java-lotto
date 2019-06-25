package lotto;

import lotto.domain.UserLottos;
import lotto.domain.WinningLotto;
import lotto.dto.LottoResultDto;
import lotto.presentation.UserLottoPresentation;
import lotto.presentation.WinningLottoPresentation;
import lotto.service.UserLottoService;
import lotto.service.WinningLottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleLottoMain {
    public static void main(String[] args) {

        UserLottoPresentation userLottoPresentation = InputView.userLottoPresentation();
        UserLottos userLottos = UserLottoService.userLottos(userLottoPresentation.getLottoMoney(), userLottoPresentation.getManualCount(), userLottoPresentation.getManualNumbers());
        OutputView.printLottos(userLottos);

        WinningLottoPresentation winningLottoPresentation = InputView.winningLottoPresentation();
        WinningLotto winningLotto = WinningLottoService.insertWinningLotto(winningLottoPresentation.getNumbers(), winningLottoPresentation.getBonus());

        OutputView.printResult(new LottoResultDto(userLottos.result(winningLotto)));
    }
}
