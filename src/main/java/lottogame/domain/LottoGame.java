package lottogame.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lottogame.domain.machine.LottoTicketIssueMachine;
import lottogame.domain.machine.LottoWinConfirmationMachine;
import lottogame.domain.machine.LottoWinNumberIssueMachine;
import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;
import lottogame.domain.ticket.LottoTicket;
import lottogame.domain.ticket.LottoTickets;
import lottogame.domain.ticket.LottoWinTicket;

public class LottoGame {

    private Money initMoney;
    private LottoTickets lottoTickets;
    private LottoNumbers drawnWinningNumbers;
    private LottoNumber drawnBonusNumber;
    private LottoWinConfirmationMachine lottoWinConfirmationMachine;

    public LottoGame() {
    }

    public void buyTickets(final Money money) {
        this.initMoney = new Money(Integer.toString(money.getValue()));
        this.lottoTickets = LottoTicketIssueMachine.issueTickets(money);
    }

    public void drawWinningNumber(final String winningNumbers, final String bonusNumber) {
        this.drawnWinningNumbers = LottoWinNumberIssueMachine.issueWinNumbers(winningNumbers);
        this.drawnBonusNumber = LottoWinNumberIssueMachine.issueBonusNumber(bonusNumber);
    }

    public Map<Rank, Integer> getLottoGameResult() {
        Map<Rank, Integer> lottoGameResult = initLottoGameResult();
        for (LottoWinTicket winningTicket : getWinningTickets()) {
            Rank rank = Rank.getRank(winningTicket);
            lottoGameResult.put(rank, lottoGameResult.get(rank) + 1);
        }
        lottoGameResult.remove(Rank.FAIL);
        return lottoGameResult;
    }

    private Map<Rank, Integer> initLottoGameResult() {
        Map<Rank, Integer> lottoGameResult = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            lottoGameResult.put(rank, 0);
        }
        return lottoGameResult;
    }

    private List<LottoWinTicket> getWinningTickets() {
        this.lottoWinConfirmationMachine = new LottoWinConfirmationMachine(drawnWinningNumbers, drawnBonusNumber);
        List<LottoWinTicket> lottoWinTickets = new ArrayList<>();
        for (LottoTicket lottoTicket : lottoTickets.toList()) {
            int matchedCount = this.lottoWinConfirmationMachine
                .countMatchedWinningNumber(lottoTicket);
            boolean isBonusMatch = this.lottoWinConfirmationMachine.isMatchBonusNumber(lottoTicket);
            lottoWinTickets.add(new LottoWinTicket(matchedCount, isBonusMatch));
        }
        return lottoWinTickets;
    }

    public double getLottoGameYield() {
        double priceAmount = 0.0;
        for (Entry<Rank, Integer> rank : getLottoGameResult().entrySet()) {
            priceAmount += rank.getKey().getPrice() * rank.getValue();
        }
        return priceAmount / initMoney.getValue();
    }

    public LottoTickets getBoughtTickets() {
        return lottoTickets;
    }
}
