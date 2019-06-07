package lotto;

import lotto.domain.Dto.UserLottoDto;
import lotto.domain.Dto.WinningLottoDto;
import lotto.domain.LottoResult;
import lotto.domain.UserLottos;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleLottoMain {
    public static void main(String[] args) {
        String lottoMoney = InputView.lottoMoney();
        String manualCount = InputView.manualLottoCount();
        UserLottoDto userLottoDto = new UserLottoDto(lottoMoney, manualCount, InputView.manualLottoNumber(manualCount));
        UserLottos userLottos = new UserLottos(userLottoDto);
        OutputView.printLottos(userLottos);
        WinningLotto winningLotto = new WinningLotto(new WinningLottoDto(InputView.winningLottoNumber(), InputView.winningLottoBonus()));
        LottoResult lottoResult = userLottos.result(winningLotto);
        OutputView.printResult(lottoResult);
    }
}
