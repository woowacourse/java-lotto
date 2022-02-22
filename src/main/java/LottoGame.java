import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LottoGame {

    //    private List<Lotto> lottos;
    private Lottos lottos;
    private Map<Integer, Integer> convertReward;
    private Map<Integer, Integer> results;

//    public LottoGame() {
//
//    }

    public LottoGame() {
        //.lottos = lottos;

        convertReward = new HashMap<>();
        convertReward.put(1, 2000000000);
        convertReward.put(2, 30000000);
        convertReward.put(3, 1500000);
        convertReward.put(4, 50000);
        convertReward.put(5, 5000);
        convertReward.put(-1, 0);


    }

    public LottoGame(List<Lotto> lottos) {
        this.lottos = new Lottos(lottos);
    }

    public List<Lotto> buyLotto(int money) {

        //lottos = new ArrayList<Lotto>();

        int lottoAmount = money / 1000;

        lottos = new Lottos(lottoAmount);

//        for (int i = 0; i < lottoAmount; ++i) {
//            lottos.getLottos().add(new Lotto());
//        }
        return lottos.getLottos();
    }

    public double getYield(List<Integer> winningNumbers, Integer bonusNumber) {

        //lottos2 = new Lottos(lottos);

        results = lottos.compareAllLotto(winningNumbers, bonusNumber);

        Set<Integer> winningRankings = results.keySet();
        int prize = 0;
        for (Integer winningRanking : winningRankings) {
            prize += results.get(winningRanking) * convertReward.get(winningRanking);
        }
        System.out.println("@@@@@prize: " + prize);
        return prize / (1000 * lottos.getSize());
    }

    public Map<Integer, Integer> getResults() {
        return results;
    }

    public Lottos getLottos() {
        return lottos;
    }


}
