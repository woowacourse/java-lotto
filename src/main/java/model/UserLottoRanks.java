package model;

import dto.LottoDto;
import java.util.EnumMap;
import java.util.List;

public class UserLottoRanks {

    private final WinningLotto winningLotto;
    private final List<LottoDto> lottos;

    public UserLottoRanks(final WinningLotto winningLotto, List<LottoDto> lottos) {
        this.winningLotto = winningLotto;
        this.lottos = lottos;
    }

    public EnumMap<Rank, Integer> getUserLottoRanks() {
        EnumMap<Rank, Integer> ranks = new EnumMap<>(Rank.class);
        initRank(ranks);

        for (LottoDto lottoDto : lottos) {
            Rank rank = Rank.getRank(winningLotto, lottoDto);
            ranks.put(rank, ranks.get(rank) + 1);
        }

        return ranks;
    }

    private void initRank(EnumMap<Rank, Integer> ranks) {
        for (Rank rank : Rank.values()) {
            ranks.put(rank, 0);
        }
    }
}
