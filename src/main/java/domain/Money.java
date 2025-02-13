package domain;

import java.util.HashMap;

public class Money {
    private int money;

    public Money(int money){
        validateRange(money);
        this.money = money;
    }

    public int calculateTotalLotto() {
        return money / 1000;
    }

    private void validateRange(int money) {
        if(money < 0) {
            throw new IllegalArgumentException("금액은 음수가 불가능 합니다.");
        }
    }

    public double calculateProfit(HashMap<LottoMatch, Integer> lottoResult) {
        int sum = 0;
        for(LottoMatch lottoMatch : lottoResult.keySet()){
            sum += lottoMatch.prize * lottoResult.get(lottoMatch);
        }
        return (double) sum / money * 100;
    }
}
