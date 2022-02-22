import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lottos {

    //int lottoAmount;
    List<Lotto> lottos;

    public Lottos(int lottoAmount) {
        //this.lottoAmount = lottoAmount;
        lottos = new ArrayList<>();
        for (int i = 0; i<lottoAmount; ++i) {
            lottos.add(new Lotto());
        }

    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Map<Integer, Integer> compareAllLotto(List<Integer> winningNumbers, int bonusNumber) {

        Map<Integer, Integer> results = new HashMap<>();
        for (int i = 0; i<lottos.size(); ++i) {
            int winningRanking = lottos.get(i).checkWinning(winningNumbers, bonusNumber);
            results.put(winningRanking, results.getOrDefault(winningRanking, 0) + 1);
        }


        return results;
    }

    public int getSize() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
