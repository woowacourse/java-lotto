package lottery;

import domain.Rank;
import domain.Statistics;

import java.util.ArrayList;
import java.util.List;

public class LotteryAssembler {
    public static StatisticsDTO createStatisticsDTO(Statistics statistics) {
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        List<CountsOfRankDTO> countsOfRank = getCountsOfRank(statistics);

        statisticsDTO.setLotteryResults(countsOfRank);
        statisticsDTO.setEarningRates(statistics.calculateEarningRates());
        return statisticsDTO;
    }

    private static List<CountsOfRankDTO> getCountsOfRank(Statistics statistics) {
        CountsOfRankDTO countsOfRankDTO;
        List<CountsOfRankDTO> lotteryResults = new ArrayList<>();

        for (Rank rank : statistics.getKeys()) {
            countsOfRankDTO = new CountsOfRankDTO();
            countsOfRankDTO.setNumberOfMatching(rank.getNumberOfMatching());
            countsOfRankDTO.setWinningMoney(rank.getWinningMoney().getAmount());
            countsOfRankDTO.setCountsOfRank(statistics.countsOf(rank));

            lotteryResults.add(countsOfRankDTO);
        }
        return lotteryResults;
    }
}
