package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoStats {
    private List<Integer> rank;

    public LottoStats() {
        this.rank = new ArrayList<>();
        for(int i=0; i<6; i++){
            rank.add(0);
        }
    }

}
