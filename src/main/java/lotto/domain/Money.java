package lotto.domain;

import java.util.Map;

public class Money {
    private static final String MONEY_INT_ERROR = "[ERROR] 숫자만 입력할 수 있습니다";
    private static final String MONEY_RANGE_ERROR = "[ERROR] 금액을 1000원 이상 입력해주세요";
    private static final String MONEY_UNIT_ERROR = "[ERROR] 금액을 1000단위로 입력해주세요";
    private static final String FORMAT = "%.2f";
    private static final int THOUSAND = 1000;
    private static int money;
    private static int earning;

    public Money(String input) {
        int inputMoney = changeToInt(input);
        validateRange(inputMoney);
        validateUnit(inputMoney);
        money = inputMoney;
    }

    private int changeToInt(String input) {
        int money;
        try {
            money = Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException(MONEY_INT_ERROR);
        }
        return money;
    }

    private void validateRange(int money) {
        if (money < THOUSAND) {
            throw new IllegalArgumentException(MONEY_RANGE_ERROR);
        }
    }

    private void validateUnit(int money) {
        if (money % THOUSAND != 0) {
            throw new IllegalArgumentException(MONEY_UNIT_ERROR);
        }
    }

    public static void findEarning(Map<Rank, Integer> countByRank) {
        int sumOfPrize = 0;
        for (Map.Entry<Rank, Integer> singleCount : countByRank.entrySet()) {
            sumOfPrize += singleCount.getKey().getPrize() * singleCount.getValue();
        }
        earning = sumOfPrize;
    }

    public static String findEarningRate() {
        double earningRate = (double) earning / (double) money;
        return String.format(FORMAT, earningRate);
    }

    public static int getEarning() {
        return earning;
    }

    public int count() {
        return money / THOUSAND;
    }
}