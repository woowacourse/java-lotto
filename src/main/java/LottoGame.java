import java.util.ArrayList;
import java.util.List;

public class LottoGame {

    private List<Lotto> lottos;

    public List<Lotto> buyLotto(int money) {

        lottos = new ArrayList<Lotto>();

        int lottoAmount = money / 1000;

        for (int i = 0; i<lottoAmount; ++i) {
            lottos.add(new Lotto());
        }
        return this.lottos;
    }
}
