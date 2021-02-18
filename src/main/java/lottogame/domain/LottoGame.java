package lottogame.domain;

import java.util.ArrayList;
import java.util.List;
import lottogame.domain.machine.LottoTicketMachine;
import lottogame.domain.machine.LottoWinningDrawingMachine;
import lottogame.domain.machine.LottoWinningMachine;
import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;
import lottogame.domain.ticket.LottoTicket;
import lottogame.domain.ticket.LottoTicketResult;
import lottogame.domain.ticket.LottoTickets;

public class LottoGame {

    private LottoTickets lottoTickets;
    private LottoNumbers drawnWinningNumbers;
    private LottoNumber drawnBonusNumber;
    private LottoWinningMachine lottoWinningMachine;

    public void buyTickets(final Money money) {
        this.lottoTickets = new LottoTicketMachine().buyTickets(money);
    }

    public void drawWinningNumber(final String winningNumbers, final String bonusNumber) {
        LottoWinningDrawingMachine lottoWinningDrawingMachine = new LottoWinningDrawingMachine();
        this.drawnWinningNumbers = lottoWinningDrawingMachine.drawing(winningNumbers);
        this.drawnBonusNumber = lottoWinningDrawingMachine.bonusDrawing(bonusNumber);
    }


    public List<LottoTicketResult> getWinningTickets() {
        lottoWinningMachine = new LottoWinningMachine(drawnWinningNumbers, drawnBonusNumber);
        List<LottoTicketResult> lottoTicketResults = new ArrayList<>();

        for (LottoTicket lottoTicket : lottoTickets.toList()) {
            int matchedCount = lottoWinningMachine.countMatchedWinningNumber(lottoTicket);
            boolean isBonusMatch = lottoWinningMachine.isMatchBonusNumber(lottoTicket);
            lottoTicketResults.add(new LottoTicketResult(matchedCount, isBonusMatch));
        }

        return lottoTicketResults;
    }
}
