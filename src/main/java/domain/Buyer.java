package domain;

import java.util.ArrayList;
import java.util.HashMap;
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

    public HashMap<LottoMatch, Integer> countLottos(WinningLotto winningLotto) {
        HashMap<LottoMatch, Integer> result = new HashMap<>();
        for(LottoMatch lottoMatch : LottoMatch.values()) {
            result.put(lottoMatch, 0);
        }

        for(Lotto lotto : lottos){
            LottoMatch matchedLotto = lotto.compareLotto(winningLotto);
            result.put(matchedLotto, result.get(matchedLotto) + 1);
        }
        return result;
    }


}
