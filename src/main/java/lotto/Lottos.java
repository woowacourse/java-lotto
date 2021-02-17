package lotto;

import java.util.ArrayList;
import java.util.Map;

public class Lottos {

    private int money;
    private int count;
    ArrayList<Lotto> lottoGroup = new ArrayList<>();

    public Lottos(String input) {
        int money = changeToInt(input);
        validateRange(money);
        validateUnit(money);
        this.money = money;
        this.count = this.money / 1000;
        generateLottoGroup();
    }

    public static int findEarning(Map<Rank, Integer> countByRank) {
        int earning = 0;
        for (Map.Entry<Rank, Integer> singleCount : countByRank.entrySet())
            earning += singleCount.getKey().getPrize() * singleCount.getValue();
        return earning;
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

    public int getCount() {
        return count;
    }

    public void generateLottoGroup() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        for (int i=0; i < count; i++) {
            Lotto generatedLotto = new Lotto(lottoGenerator.generateLottoNums());
            lottoGroup.add(generatedLotto);
        }
    }

    public ArrayList<Lotto> getLottoGroup() {
        return this.lottoGroup;
    }
}
