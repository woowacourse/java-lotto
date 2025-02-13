package domain;

import domain.dto.GetLottoDto;
import domain.dto.GetLottosDto;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;

public class Lottos {

    private List<Lotto> lottos;

    public Lottos(){
        lottos= new ArrayList<>();
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

    public void getResult(WinningLotto winningLotto) {
        EnumMap<Rank, Integer> countRank = countMatchNumbers(winningLotto);
        for (Entry<Rank, Integer> rankIntegerEntry : countRank.entrySet()) {
            System.out.println(rankIntegerEntry.getKey() + " " + rankIntegerEntry.getValue());
        }
    }

    private EnumMap<Rank, Integer> countMatchNumbers(WinningLotto winningLotto) {
        EnumMap<Rank, Integer> countRank = new EnumMap<>(Rank.class);

        for (Lotto lotto : lottos) {
            Rank matchRank = lotto.countMatchNumbers(winningLotto);
            countRank.put(matchRank, countRank.getOrDefault(matchRank, 0) + 1);
        }

        return countRank;
    }
}
