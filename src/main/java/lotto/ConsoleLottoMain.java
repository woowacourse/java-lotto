package lotto;

import lotto.domain.UserLottosCreator;
import lotto.domain.UserTicketCreator;
import lotto.domain.WinningLottoCreator;
import lotto.domain.WinningTicketCreator;
import lotto.domain.core.UserTickets;
import lotto.domain.core.WinningTicket;
import lotto.domain.exceptions.ExceptionMessages;
import lotto.domain.exceptions.LottoTicketException;
import lotto.dto.LottoResultDto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleLottoMain {
    public static void main(String[] args) {
        String lottoMoney = InputView.lottoMoney();
        int manualCount = toManualLottoCount(InputView.manualLottoCount());

        UserTicketCreator userTicketCreator = new UserLottosCreator(lottoMoney, manualCount, InputView.manualLottoNumber(manualCount));
        UserTickets userLottos = userTicketCreator.create();
        OutputView.printLottos(userLottos);

        WinningTicketCreator winningTicketCreator = new WinningLottoCreator(InputView.winningLottoNumber(), InputView.winningLottoBonus());
        WinningTicket winningLotto = winningTicketCreator.create();
        OutputView.printResult(new LottoResultDto(userLottos.result(winningLotto)));
    }

    private static int toManualLottoCount(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new LottoTicketException(ExceptionMessages.TICKET.message());
        }
    }
}
