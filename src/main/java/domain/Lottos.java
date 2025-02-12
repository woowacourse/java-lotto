package domain;


import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(int money) {
        int quantity = money / 1000;

        for (int i = 0; i < quantity; i++) {
            lottos.add(new Lotto());
        }
    }

    public int getQuantity() {
        return lottos.size();
    }
}
