package domain;

import java.util.EnumMap;

class MatchingStatistics {
    private EnumMap<Rank, CountOfRank> countsOfRanks;

    private MatchingStatistics(EnumMap<Rank, CountOfRank> countsOfRanks) {
        this.countsOfRanks = countsOfRanks;
    }

    static MatchingStatistics of(Rank[] values) {
        EnumMap<Rank, CountOfRank> countsOfRanks = new EnumMap<>(Rank.class);

        initialize(countsOfRanks, values);
        return new MatchingStatistics(countsOfRanks);
    }

    private static void initialize(EnumMap<Rank, CountOfRank> countsOfRanks, Rank[] values) {
        for(Rank rank : values) {
            countsOfRanks.put(rank, new CountOfRank());
        }
    }

    void add(Rank matchingResult) {
        countsOfRanks.get(matchingResult).countUp();
    }

    int countsOf(Rank matchingResult) {
        CountOfRank countOfRank = countsOfRanks.get(matchingResult);

        return countOfRank.getCount();
    }
}
