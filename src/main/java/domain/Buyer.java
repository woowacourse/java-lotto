package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Buyer {
    private Money money;
    private List<Lotto> lottos = new ArrayList<>();

    public Buyer(Money money){
        this.money = money;
    }

    public List<Lotto> createLottos(){
        int totalLotto = money.calculateTotalLotto();
        for(int i=0; i< totalLotto; i++){
            lottos.add(new Lotto());
        }
        return lottos;
    }

    public String createResult() {
        String result = "";
        for(Lotto lotto : lottos){
            result += lotto.toString() +"\n";
        }
        return result;
    }
}
