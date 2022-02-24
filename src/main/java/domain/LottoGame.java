package domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoGame {

    //    private List<Lotto> lottos;
    private static final int LOTTO_PRICE = 1000;

    private Lottos lottos;
    private Map<Integer, Integer> convertReward;

//    public domain.LottoGame() {
//
//

//    public domain.LottoGame() {
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
        convertReward = new HashMap<>();
        convertReward.put(1, 2000000000);
        convertReward.put(2, 30000000);
        convertReward.put(3, 1500000);
        convertReward.put(4, 50000);
        convertReward.put(5, 5000);
        convertReward.put(-1, 0);

        this.lottos = new Lottos(lottos);
    }

    public Lottos buyLotto(Money money) {

        int lottoAmount = money.money() / LOTTO_PRICE;

        lottos = new Lottos(lottoAmount);

        return lottos;

    }

    public void makeResult(List<Integer> winningNumbers, Integer bonusNumber) {
        lottos.compareAllLotto(winningNumbers, bonusNumber);
    }

    public double getYield() {


        int prize = 0;

        prize = Arrays.stream(Rewards.values()).map(x -> Rewards.calculateYield(x)).reduce(0, Integer::sum);



        //Integer sum1 = numbers.stream().reduce(0, Integer::sum);



//
//        Set<Integer> winningRankings = results.keySet();
//        int prize = 0;
//        for (Integer winningRanking : winningRankings) {
//            prize += results.get(winningRanking) * convertReward.get(winningRanking);
//        }
//        System.out.println("@@@@@prize: " + prize);
        return prize / (1000 * lottos.getSize());
    }
//
//    public R getResults() {
//        return domain.Rewards;
//    }

    public Lottos getLottos() {
        return lottos;
    }


}
