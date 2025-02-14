package domain;

import domain.dto.GetLottoDto;
import domain.dto.GetLottosDto;
import domain.dto.GetResultDto;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;

public class Lottos {

    private List<Lotto> lottos;

    public Lottos() {
        lottos = new ArrayList<>();
    }

    public void add(Lotto lotto) {
        lottos.add(lotto);
    }


    public GetLottosDto getLottosDto() {
        List<GetLottoDto> getLottoDtos = new ArrayList<>();
        for (Lotto lotto : lottos) {
            getLottoDtos.add(lotto.getLottoDto());
        }
        return new GetLottosDto(getLottoDtos);
    }

    public GetResultDto getResult(WinningLotto winningLotto, Amount amount) {
        EnumMap<Rank, Integer> countRank = countMatchNumbers(winningLotto);

        long sum = 0L;
        for (Entry<Rank, Integer> rankIntegerEntry : countRank.entrySet()) {
            sum += Rank.getTotalPrize(rankIntegerEntry.getKey(), rankIntegerEntry.getValue());
        }

        double profit = amount.calculateProfit(sum);
        return new GetResultDto(countRank, profit);
    }

    private EnumMap<Rank, Integer> countMatchNumbers(WinningLotto winningLotto) {
        EnumMap<Rank, Integer> countRank = new EnumMap<>(Rank.class);

        for (Rank rank : Rank.values()) {
            countRank.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            Rank matchRank = lotto.countMatchNumbers(winningLotto);
            countRank.put(matchRank, countRank.get(matchRank) + 1);
        }

        return countRank;
    }
}
