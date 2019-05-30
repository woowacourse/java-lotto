package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistics {
    private Map<Rank, Integer> map = new HashMap<>();

    public Statistics() {
        for (Rank rank : Rank.values()) {
            map.put(rank, 0);
        }
    }

    public void calculateResult(Lottoes lottoes, WinningLotto winningLotto){
        List<Rank> ranks = lottoes.getRanks(winningLotto);
        for(Rank rank : ranks){
            putRank(rank);
        }
    }

    public Map<Rank,Integer> getResult(){
        return map;
    }

    public void putRank(Rank rank) {
        map.put(rank, map.get(rank) + 1);
    }

}
