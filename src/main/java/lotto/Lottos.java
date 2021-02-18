package lotto;

import java.util.ArrayList;
import java.util.Map;

public class Lottos {

    private static int money;
    private static int count;
    private static int earning;
    ArrayList<Lotto> lottoGroup = new ArrayList<>();

    public Lottos(String input) {
        int money = changeToInt(input);
        validateRange(money);
        validateUnit(money);
        this.money = money;
        this.count = this.money / 1000;
        generateLottoGroup();
    }

    public static void findEarning(Map<Rank, Integer> countByRank) {
        int sumOfPrize = 0;
        for (Map.Entry<Rank, Integer> singleCount : countByRank.entrySet())
            sumOfPrize += singleCount.getKey().getPrize() * singleCount.getValue();
        earning = sumOfPrize;
    }

    public static String findEarningRate(int money, int earning) {
        double earningRate = (double) earning / (double) money;
        return String.format("%.2f", earningRate);
    }

    private int changeToInt(String input) {
        int money;
        try {
            money = Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력할 수 있습니다");
        }
        return money;
    }

    private void validateRange(int money) {
        if (money < 1000)
            throw new IllegalArgumentException("[ERROR] 금액을 1000원 이상 입력해주세요");
    }

    private void validateUnit(int money) {
        if (money % 1000 != 0)
            throw new IllegalArgumentException("[ERROR] 금액을 1000단위로 입력해주세요");
    }

    public void generateLottoGroup() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        for (int i=0; i < count; i++) {
            Lotto generatedLotto = new Lotto(lottoGenerator.generateLottoNums());
            lottoGroup.add(generatedLotto);
        }
    }

    public int getCount() {
        return count;
    }

    public static int getEarning() {
        return earning;
    }

    public ArrayList<Lotto> getLottoGroup() {
        return this.lottoGroup;
    }
}
