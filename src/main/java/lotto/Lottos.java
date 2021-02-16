package lotto;

import java.util.ArrayList;

public class Lottos {

    private int money;
    private int count;
    ArrayList<ArrayList<Integer>> lottos = new ArrayList<>();

    public Lottos(String input) {
        int money = changeToInt(input);
        validateRange(money);
        validateUnit(money);
        this.money = money;
        this.count = this.money / 1000;
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
        if (money < 1000) {
            throw new IllegalArgumentException("[ERROR] 금액을 1000원 이상 입력해주세요");
        }
    }

    private void validateUnit(int money) {
        if (money % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 금액을 1000단위로 입력해주세요");
        }
    }

    public int getCount() {
        return count;
    }

    public void generateLottos() {
        for (int i=0; i < count; i++) {
            Lotto generatedLotto = new Lotto();
            lottos.add(generatedLotto.getLotto());
        }
    }
}
