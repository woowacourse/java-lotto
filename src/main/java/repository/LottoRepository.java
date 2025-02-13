package repository;

import domain.Lotto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoRepository {
    private static List<Lotto> lottos=new ArrayList<>();

    private LottoRepository() {
    }

    public static LottoRepository create(){
        return new LottoRepository();
    }

   public void addLotto(Lotto lotto){
       lottos.add(lotto);
   }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
