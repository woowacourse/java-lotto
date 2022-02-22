import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(){
        lottos = new ArrayList<>();
    }
    public Lottos(List<Lotto> lottos){
        this.lottos = lottos;
    }


    public void generateLottos(int count) {
        for(int i=0; i<count; i++){
            lottos.add(Lotto.generateNumber());
        }
        new Lottos(lottos);
    }

    public int size() {
        return lottos.size();
    }
}
// list = {1,2,3~,45}
// Collections.suffle(list);
