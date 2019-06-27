package lotto;

import lotto.domain.UserLottos;
import lotto.domain.WinningLotto;
import lotto.dto.LottoResultDto;
import lotto.service.UserLottoService;
import lotto.service.UserLottoTranslator;
import lotto.service.WinningLottoService;
import lotto.service.WinningLottoTranslator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleLottoMain {
    public static void main(String[] args) {

        UserLottoTranslator userLottoTranslator = InputView.userLottoPresentation();
        UserLottos userLottos = UserLottoService.userLottos(userLottoTranslator);
        OutputView.printLottos(userLottos);

        WinningLottoTranslator winningLottoTranslator = InputView.winningLottoPresentation();
        WinningLotto winningLotto = WinningLottoService.insertWinningLotto(1, winningLottoTranslator.getNumbers(), winningLottoTranslator.getBonus());

        OutputView.printResult(new LottoResultDto(userLottos.result(winningLotto)));
    }
}
