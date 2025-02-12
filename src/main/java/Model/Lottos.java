package Model;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private List<Lotto> lottos;

    public Lottos(int count) {
        this.lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(LottoCreator.createLotto()));
        }
    }

    public void printLottoNumbers(){
        for (Lotto l : this.lottos){
            System.out.println(l.printLottoNumber());
        }
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

}
