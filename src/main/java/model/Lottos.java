package model;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();

    public void addLotto(Lotto lotto) {
        lottos.add(lotto);
    }

}
