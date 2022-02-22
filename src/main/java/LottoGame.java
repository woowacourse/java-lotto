import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LottoGame {

    private List<Lotto> lottos;
    private Lottos lottos2;
    private Map<Integer, Integer> convertReward;

    public LottoGame() {

    }

    public LottoGame(List<Lotto> lottos) {
        this.lottos = lottos;

        convertReward = new HashMap<>();
        convertReward.put(1, 2000000000);
        convertReward.put(2, 30000000);
        convertReward.put(3, 1500000);
        convertReward.put(4, 50000);
        convertReward.put(5, 5000);
        convertReward.put(-1, 0);


    }

    public List<Lotto> buyLotto(int money) {

        lottos = new ArrayList<Lotto>();

        int lottoAmount = money / 1000;

        for (int i = 0; i < lottoAmount; ++i) {
            lottos.add(new Lotto());
        }
        return this.lottos;
    }

    public double getYield(List<Integer> winningNumbers, Integer bonusNumber) {

        lottos2 = new Lottos(lottos);

        Map<Integer, Integer> results = lottos2.compareAllLotto(winningNumbers, bonusNumber);

        Set<Integer> winningRankings = results.keySet();
        int prize = 0;
        for (Integer winningRanking : winningRankings) {
            prize += results.get(winningRanking) * convertReward.get(winningRanking);
        }
        System.out.println("@@@@@prize: " + prize);
        return prize / (1000 * lottos2.getSize());
    }


}
