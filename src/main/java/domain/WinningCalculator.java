package domain;

import java.util.HashMap;
import java.util.Map;

public class WinningCalculator {
    private static final int INITIAL_COUNT = 0;
    private static final int HAS_BONUS = 5;

    Map<PrizeType, Integer> prizeInfo;

    public WinningCalculator() {
        initializePrizeInfo();
    }

    private void initializePrizeInfo() {
        this.prizeInfo = new HashMap<>();
        this.prizeInfo.put(PrizeType.THREE, INITIAL_COUNT);
        this.prizeInfo.put(PrizeType.FOUR, INITIAL_COUNT);
        this.prizeInfo.put(PrizeType.FIVE, INITIAL_COUNT);
        this.prizeInfo.put(PrizeType.FIVE_WITH_BONUS, INITIAL_COUNT);
        this.prizeInfo.put(PrizeType.SIX, INITIAL_COUNT);
    }

    public void calculateWinningCount(LottoTickets lottoTickets, WinningLottoTicket winningLottoTicket) {
        countWinningLottoNumber(lottoTickets, winningLottoTicket);
    }

    private void countWinningLottoNumber(LottoTickets lottoTickets, WinningLottoTicket winningLottoTicket) {
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            PrizeType prizeType = compareNumberWithWinningLottoNumber(lottoTicket, winningLottoTicket);
            addPrizeInfoValue(prizeType);
        }
    }

    private PrizeType compareNumberWithWinningLottoNumber(LottoTicket lottoTicket,
                                                          WinningLottoTicket winningLottoTicket) {
        int winningCount = 0;
        for (int number : winningLottoTicket.getWinningLottoTicket()) {
            winningCount = checkLottoTicketHasWinningNumber(lottoTicket, winningCount, number);
        }
        if (winningCount == HAS_BONUS) {
            return PrizeType.getPrizeTypeForWinningCount(winningCount,
                    isLottoTicketHasBonusNumber(winningLottoTicket, lottoTicket));
        }
        return PrizeType.getPrizeTypeForWinningCount(winningCount, false);
    }

    private int checkLottoTicketHasWinningNumber(LottoTicket lottoTicket, int winningCount, int number) {
        if (lottoTicket.hasNumber(number)) {
            winningCount = winningCount + 1;
        }
        return winningCount;
    }

    private boolean isLottoTicketHasBonusNumber(WinningLottoTicket winningLottoTicket, LottoTicket lottoTicket) {
        return lottoTicket.hasNumber(winningLottoTicket.getBonusNumber());
    }

    private void addPrizeInfoValue(PrizeType prizeType) {
        if (prizeType == null) {
            return;
        }
        int originalPrizeCount = prizeInfo.get(prizeType);
        this.prizeInfo.put(prizeType, originalPrizeCount + 1);
    }

    public int getPrizeTypeValue(PrizeType prizeType) {
        return this.prizeInfo.get(prizeType);
    }
}
