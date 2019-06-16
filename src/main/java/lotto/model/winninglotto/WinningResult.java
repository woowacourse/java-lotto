package lotto.model.winninglotto;

import lotto.model.lotto.LottoTickets;
import lotto.model.lotto.LottoRank;
import lotto.model.lottostore.Price;

import java.util.EnumMap;

public class WinningResult {
    private static final String WINNING_MONEY_PRINT_FORMAT = "(%d원)";
    private static final String COUNT_OF_WIN_PRINT_FORMAT = " - %d개";
    private static final String ROI_PRINT_FORMAT = "총 수익률은 %d％입니다.";
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final int PERCENTAGE = 100;
    private static final long UNKNOWN_VALUE = 0L;
    private static final long DEFAULT_VALUE = 1L;

    private EnumMap<LottoRank, Long> rankCountMap;

    private WinningResult(LottoTickets lottoTickets, WinningLotto winningLotto) {
        rankCountMap = mapTickets(lottoTickets, winningLotto);
    }

    public static WinningResult of(LottoTickets lottoTickets, WinningLotto winningLotto) {
        return new WinningResult(lottoTickets, winningLotto);
    }

    private EnumMap<LottoRank, Long> mapTickets(LottoTickets lottoTickets, WinningLotto winningLotto) {
        EnumMap<LottoRank, Long> rankCountMap = new EnumMap<>(LottoRank.class);
        lottoTickets.stream()
                .forEach(lottoTicket -> rankCountMap.merge(lottoTicket.convertToLottoRank(winningLotto), DEFAULT_VALUE, Long::sum));

        return rankCountMap;
    }

    public long getROI() {
        long roi = 0;
        long countSum = 0;
        for (LottoRank lottoRank : LottoRank.values()) {
            long countOfMatchingLotto = rankCountMap.getOrDefault(lottoRank, UNKNOWN_VALUE);
            System.out.println(countOfMatchingLotto);
            roi += lottoRank.calculateWinningMoney(countOfMatchingLotto);
            countSum += countOfMatchingLotto;
        }
        return (roi * PERCENTAGE) / (countSum * Price.LOTTO_TICKET_PRICE.getPrice());
    }

    public Long getRankCount(String rank){
        return rankCountMap.getOrDefault(LottoRank.valueOf(rank), UNKNOWN_VALUE);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (LottoRank lottoRank : LottoRank.values()) {
            stringBuilder.append(lottoRank)
                    .append(String.format(WINNING_MONEY_PRINT_FORMAT, lottoRank.calculateWinningMoney(DEFAULT_VALUE)))
                    .append(String.format(COUNT_OF_WIN_PRINT_FORMAT, rankCountMap.getOrDefault(lottoRank, UNKNOWN_VALUE)))
                    .append(NEW_LINE);
        }
        stringBuilder.append(String.format(ROI_PRINT_FORMAT, getROI()));

        return stringBuilder.toString();
    }
}



