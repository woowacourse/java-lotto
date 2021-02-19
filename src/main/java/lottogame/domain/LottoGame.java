package lottogame.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lottogame.domain.machine.LottoTicketMachine;
import lottogame.domain.machine.LottoWinningDrawingMachine;
import lottogame.domain.machine.LottoWinningMachine;
import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;
import lottogame.domain.ticket.LottoTicket;
import lottogame.domain.ticket.LottoTicketResult;
import lottogame.domain.ticket.LottoTickets;

public class LottoGame {

    private Money initMoney;
    private LottoTickets lottoTickets;
    private LottoNumbers drawnWinningNumbers;
    private LottoNumber drawnBonusNumber;
    private LottoWinningMachine lottoWinningMachine;

    public LottoGame() {
    }

    public void buyTickets(final Money money) {
        this.initMoney = new Money(Integer.toString(money.getValue()));
        this.lottoTickets = new LottoTicketMachine().buyTickets(money);
    }

    public void drawWinningNumber(final String winningNumbers, final String bonusNumber) {
        LottoWinningDrawingMachine lottoWinningDrawingMachine = new LottoWinningDrawingMachine();
        this.drawnWinningNumbers = lottoWinningDrawingMachine.drawing(winningNumbers);
        this.drawnBonusNumber = lottoWinningDrawingMachine.bonusDrawing(bonusNumber);
    }

    public LottoTickets getBoughtTickets() {
        return lottoTickets;
    }

    public Map<Rank, Integer> getLottoGameResult() {
        Map<Rank, Integer> lottoGameResult = initLottoGameResult();
        for (LottoTicketResult winningTicket : getWinningTickets()) {
            Rank rank = Rank.getRank(winningTicket);
            lottoGameResult.put(rank, lottoGameResult.get(rank) + 1);
        }
        lottoGameResult.remove(Rank.FAIL);
        return lottoGameResult;
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

    private Map<Rank, Integer> initLottoGameResult() {
        Map<Rank, Integer> lottoGameResult = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            lottoGameResult.put(rank, 0);
        }
        return lottoGameResult;
    }

    public double getLottoGameYield() {
        double priceAmount = 0.0;
        for (Entry<Rank, Integer> rank : getLottoGameResult().entrySet()) {
            priceAmount += rank.getKey().getPrice() * rank.getValue();
        }
        return priceAmount / initMoney.getValue();
    }
}
