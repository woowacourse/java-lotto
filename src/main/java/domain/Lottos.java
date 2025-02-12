package domain;


import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos = new ArrayList<>();

    private Lottos(int money) {
        int quantity = money / 1000;

        for (int i = 0; i < quantity; i++) {
            lottos.add(new Lotto());
        }
    }

    public static Lottos of(String input) {
        int money = Integer.parseInt(input);
        return new Lottos(money);
    }

    public int getQuantity() {
        return lottos.size();
    }

    public List<String> getLottoNumbers() {
        return lottos.stream()
                .map(Lotto::getNumbers)
                .toList();
    }
}
