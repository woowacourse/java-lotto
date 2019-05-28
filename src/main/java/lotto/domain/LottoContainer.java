package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoContainer {
    private List<Lotto> lottoContainer = new ArrayList<>();

    public LottoContainer(List<Lotto> self,List<Lotto> auto) {
        for(Lotto selfLotto : self){
            lottoContainer.add(selfLotto);
        }
        for(Lotto autoLotto : auto){
            lottoContainer.add(autoLotto);
        }
    }

    public int getSize(){
        return lottoContainer.size();
    }
}
