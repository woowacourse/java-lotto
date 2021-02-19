package lottogame.domain;

import lottogame.domain.machine.LottoTicketMachine;
import lottogame.domain.machine.LottoWinningDrawingMachine;
import lottogame.domain.machine.LottoWinningMachine;
import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;
import lottogame.domain.ticket.LottoTicket;
import lottogame.domain.ticket.LottoTicketResult;
import lottogame.domain.ticket.LottoTickets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoGame {

    private Money initMoney;
    private LottoTickets lottoTickets;
    private LottoNumbers drawnWinningNumbers;
    private LottoNumber drawnBonusNumber;
    private LottoWinningMachine lottoWinningMachine;
    private LottoGameResult lottoGameResult;

    public void buyTickets(final Money money) {
        this.initMoney = new Money(Integer.toString(money.getValue()));
        this.lottoTickets = new LottoTicketMachine().buyTickets(money);
    }

    public void drawWinningNumber(final String winningNumbers, final String bonusNumber) {
        LottoWinningDrawingMachine lottoWinningDrawingMachine = new LottoWinningDrawingMachine();
        this.drawnWinningNumbers = lottoWinningDrawingMachine.drawing(winningNumbers);
        this.drawnBonusNumber = lottoWinningDrawingMachine.bonusDrawing(bonusNumber);
    }

    public LottoTickets getBroughtTickets() {
        return lottoTickets;
    }

    private List<LottoTicketResult> getWinningTickets() {
        lottoWinningMachine = new LottoWinningMachine(drawnWinningNumbers, drawnBonusNumber);
        List<LottoTicketResult> lottoTicketResults = new ArrayList<>();

        for (LottoTicket lottoTicket : lottoTickets.toList()) {
            int matchedCount = lottoWinningMachine.countMatchedWinningNumber(lottoTicket);
            boolean isBonusMatch = lottoWinningMachine.isMatchBonusNumber(lottoTicket);
            lottoTicketResults.add(new LottoTicketResult(matchedCount, isBonusMatch));
        }
        return lottoTicketResults;
    }

    public Map<Rank, Integer> getLottoGameResult() {
        lottoGameResult = new LottoGameResult(getWinningTickets());
        return lottoGameResult.toMap();
    }

    public double getLottoGameYield() {
        return lottoGameResult.getPriceAmount() / initMoney.getValue();
    }
}
