package lotto;

import lotto.domain.LottoResult;
import lotto.domain.TicketModel.LottoCreator;
import lotto.domain.TicketModel.WinningLotto;
import lotto.domain.TicketModel.WinningTicket;
import lotto.domain.UserLottos;
import lotto.dto.UserLottoDto;
import lotto.dto.WinningLottoDto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleLottoMain {
    public static void main(String[] args) {
        String lottoMoney = InputView.lottoMoney();
        String manualCount = InputView.manualLottoCount();
        UserLottoDto userLottoDto = new UserLottoDto(lottoMoney, manualCount, InputView.manualLottoNumber(manualCount));
        UserLottos userLottos = new UserLottos(userLottoDto);
        OutputView.printLottos(userLottos);
        WinningTicket winningLotto =
                new WinningLotto(new WinningLottoDto(InputView.winningLottoNumber(),
                        InputView.winningLottoBonus()), new LottoCreator());
        LottoResult lottoResult = userLottos.result(winningLotto);
        OutputView.printResult(lottoResult);
    }
}
