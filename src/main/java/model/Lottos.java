package model;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(int ticketAmount) {
        lottos = new ArrayList<>();
        for (int i = 0; i < ticketAmount; i++) {
            lottos.add(new Lotto());
        }
    }

}
