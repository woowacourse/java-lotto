package Model;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private List<Lotto> lottos;

    public Lottos(int price) {
        this.lottos = new ArrayList<>();
        int count = lottoCount(price);
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(LottoCreator.createLotto()));
        }
    }

    public int lottoCount(int price) {
        return price / 1000;
    }

    public int lottoSize() {
        return this.lottos.size();
    }

    public void printLottoNumbers() {
        for (Lotto l : this.lottos) {
            System.out.println(l.printLottoNumber());
        }
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

}
