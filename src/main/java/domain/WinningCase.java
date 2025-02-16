package domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public enum WinningCase {

    ELSE(0,0,false),
    THREE_SAME(5000,3,false),
    FOUR_SAME(50000,4,false),
    FIVE_SAME(1500000,5,false),
    FIVE_BONUS_SAME(30000000,5,true),
    SIX_SAME(2000000000,6,false);

    private final int winningMoney;
    private final int sameCount;
    private final boolean bonusCase;

    WinningCase(int winningMoney, int sameCount, boolean bonusCase) {
        this.winningMoney = winningMoney;
        this.sameCount = sameCount;
        this.bonusCase = bonusCase;
    }

    public static WinningCase getWinningCase(int sameCount, boolean isBonus) {
        return Arrays.stream(WinningCase.values()).filter((o)->
            o.sameCount == sameCount && isBonus == o.bonusCase
        ).findFirst().orElse(ELSE);
    }

    public static Map<WinningCase,Integer> toMap(){
        Map<WinningCase, Integer> winningCaseIntegerMap = new LinkedHashMap<>();
        for(WinningCase winningCase : WinningCase.values()){
            winningCaseIntegerMap.put(winningCase,0);
        }
        return winningCaseIntegerMap;
    }

    public long calculateEarnMoney(int winningCaseCount) {
        return winningCaseCount * winningMoney;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public int getSameCount() {
        return sameCount;
    }
}
