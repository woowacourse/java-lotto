package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private List<Lotto> lottos;

    public Lottos(){
        lottos= new ArrayList<>();
    }

    public void add(Lotto lotto) {
        lottos.add(lotto);
    }

}
