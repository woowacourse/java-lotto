package lotto;

import lotto.domain.LottoResult;
import lotto.domain.UserLottos;
import lotto.domain.WinningLotto;
import lotto.domain.exceptions.ExceptionMessages;
import lotto.domain.exceptions.LottoTicketException;
import lotto.domain.ticket.LottoCreator;
import lotto.domain.ticket.WinningTicket;
import lotto.dto.UserLottoDto;
import lotto.dto.WinningLottoDto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleLottoMain {
    public static void main(String[] args) {
        String lottoMoney = InputView.lottoMoney();
        int manualCount;
        try {
            manualCount = Integer.parseInt(InputView.manualLottoCount());
        } catch (NumberFormatException e) {
            throw new LottoTicketException(ExceptionMessages.TICKET.message());
        }
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
