package lotto;

import lotto.domain.*;
import lotto.domain.exceptions.ExceptionMessages;
import lotto.domain.exceptions.LottoTicketException;
import lotto.domain.ticket.WinningTicket;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleLottoMain {
    public static void main(String[] args) {
        String lottoMoney = InputView.lottoMoney();
        int manualCount = toManualLottoCount(InputView.manualLottoCount());
        UserLottoSeed userLottoSeed = new UserLottoSeed(lottoMoney, manualCount, InputView.manualLottoNumber(manualCount));
        UserLottos userLottos = new UserLottos(userLottoSeed.getTickets());
        OutputView.printLottos(userLottos);
        WinningLottoSeed winningLottoSeed = new WinningLottoSeed(InputView.winningLottoNumber(), InputView.winningLottoBonus());
        WinningTicket winningLotto = new WinningLotto(winningLottoSeed.getWinningTicket(), winningLottoSeed.getBonus());
        LottoResult lottoResult = userLottos.result(winningLotto);
        OutputView.printResult(lottoResult.results());
    }

    private static int toManualLottoCount(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new LottoTicketException(ExceptionMessages.TICKET.message());
        }
    }
}
