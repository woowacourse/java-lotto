package lottogame.domain;

import lottogame.domain.machine.LottoTicketMachine;
import lottogame.domain.machine.LottoWinningDrawingMachine;
import lottogame.domain.machine.LottoWinningMachine;
import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;
import lottogame.domain.ticket.LottoTicketResult;
import lottogame.domain.ticket.LottoTickets;

import java.util.List;
import java.util.Map;

public class LottoGame {

    private LottoNumber drawnBonusNumber;
    private LottoNumbers drawnWinningNumbers;
    private LottoGameResult lottoGameResult;
    private final LottoTickets lottoTickets = new LottoTickets();
    private final LottoTicketMachine lottoTicketMachine = new LottoTicketMachine();

    public void buyManualTickets(final Money money, final List<String> LottoNumbersGroup) {
        for (String lottoNumbers : LottoNumbersGroup) {
            this.lottoTickets.add(lottoTicketMachine.buyManualTicket(money, lottoNumbers));
        }
    }

    public void buyAutoTickets(final Money money) {
        this.lottoTickets.concat(lottoTicketMachine.buyAutoTickets(money));
    }

    public void drawWinningNumber(final String winningNumbers, final String bonusNumber) {
        LottoWinningDrawingMachine lottoWinningDrawingMachine = new LottoWinningDrawingMachine();
        this.drawnWinningNumbers = lottoWinningDrawingMachine.drawing(winningNumbers);
        this.drawnBonusNumber = lottoWinningDrawingMachine.bonusDrawing(bonusNumber);
    }

    public LottoTickets getBroughtTickets() {
        return lottoTickets;
    }

    public Map<Rank, Integer> getLottoGameResult() {
        lottoGameResult = new LottoGameResult(getTicketsResult());
        return lottoGameResult.toMap();
    }

    private List<LottoTicketResult> getTicketsResult() {
        LottoWinningMachine lottoWinningMachine = new LottoWinningMachine(drawnWinningNumbers, drawnBonusNumber);
        return lottoTickets.getLottoTicketResults(lottoWinningMachine);
    }

    public double getLottoGameYield() {
        return lottoGameResult.getLottoGameYield(lottoTickets.getCostUsedToBuy());
    }
}
