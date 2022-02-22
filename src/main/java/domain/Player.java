package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private Money money;
    private List<Lotto> lottos;

    public Player(Money money){
        this.money = money;
        this.lottos = purchaseLotto();
    }

    private int getNumberOfPurchases(){
        return money.determineQuantity();
    }

    public List<Lotto> purchaseLotto() {
        int numberOfPurchases = getNumberOfPurchases();
        List<Lotto> lottos = new ArrayList<>();
        for(int i = 0; i < numberOfPurchases; i++){
            lottos.add(new Lotto(LottoFactory.generateAutoLotto()));
        }
        return lottos;
    }





}
