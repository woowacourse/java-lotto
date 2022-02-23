import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(int amount) {
        this.lottos = new ArrayList<>();
        while (amount-- > 0) {
            Lotto lotto = new Lotto();
            lottos.add(lotto);
        }
    }

    int getCount() {
        return lottos.size();
    }
}
