package lotto.model.winninglotto;

import lotto.model.lotto.LottoTickets;
import lotto.model.lottorank.LottoRank;
import lotto.model.lottostore.Price;

import java.util.EnumMap;

public class WinningStatistics {
    private EnumMap<LottoRank, Long> rankCountMap;
    private static final String WINNING_MONEY_PRINT_FORMAT = "(%d원)";
    private static final String COUNT_OF_WIN_PRINT_FORMAT = "- %d개";
    private static final String ROI_PRINT_FORMAT = "총 수익률은 %d％입니다.";
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final int PERCENTAGE = 100;
    private static final long UNKNOWN_VALUE = 0L;
    private static final long DEFAULT_VALUE = 1L;

    public WinningStatistics(LottoTickets lottoTickets, WinningLotto winningLotto) {
        rankCountMap = mapTickets(lottoTickets, winningLotto);
    }

    private EnumMap<LottoRank, Long> mapTickets(LottoTickets lottoTickets, WinningLotto winningLotto) {
        EnumMap<LottoRank, Long> rankCountMap = new EnumMap<>(LottoRank.class);
        lottoTickets.stream()
                .forEach(lottoTicket -> rankCountMap.merge(lottoTicket.convertToLottoRank(winningLotto), DEFAULT_VALUE, Long::sum));

        return rankCountMap;
    }

    public long calculateROI() {
        long roi = 0;
        long countSum = 0;

        for (LottoRank lottoRank : LottoRank.values()) {
            long countOfLottoRank = rankCountMap.getOrDefault(lottoRank, UNKNOWN_VALUE);
            roi += lottoRank.getWinningMoney() * countOfLottoRank;
            countSum += countOfLottoRank;
        }
        return (roi * PERCENTAGE) / (countSum * Price.LOTTO_TICKET_PRICE.getPrice());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (LottoRank lottoRank : LottoRank.values()) {
            stringBuilder.append(lottoRank.getNumberMatchType())
                    .append(String.format(WINNING_MONEY_PRINT_FORMAT, lottoRank.getWinningMoney()))
                    .append(String.format(COUNT_OF_WIN_PRINT_FORMAT, rankCountMap.getOrDefault(lottoRank, UNKNOWN_VALUE)))
                    .append(NEW_LINE);
        }
        stringBuilder.append(String.format(ROI_PRINT_FORMAT, calculateROI()));

        return stringBuilder.toString();
    }
}



