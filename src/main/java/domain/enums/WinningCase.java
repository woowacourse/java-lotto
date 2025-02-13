package domain.enums;

import java.text.Format;
import java.util.Arrays;
import java.util.List;

public enum WinningCase {
    ELSE("",0,0,false),
    THREE_SAME("%d개 일치 (%d원)- %d개",5000,3,false),
    FOUR_SAME("%d개 일치 (%d원)- %d개",50000,4,false),
    FIVE_SAME("%d개 일치 (%d원)- %d개",1500000,5,false),
    FIVE_BONUS_SAME("%d개 일치 (%d원)- %d개",30000000,5,true),
    SIX_SAME("%d개 일치 (%d원)- %d개",2000000000,6,false);

    private final String winningCaseFormat;
    private final int winningMoney;
    private final int sameCount;
    private final boolean bonusCase;

    WinningCase(String winningCaseFormat, int winningMoney, int sameCount, boolean bonusCase) {
        this.winningCaseFormat = winningCaseFormat;
        this.winningMoney = winningMoney;
        this.sameCount = sameCount;
        this.bonusCase = bonusCase;
    }

    public static WinningCase getWinningCase(int sameCount, boolean isBonus) {
        return Arrays.stream(WinningCase.values()).filter((o)->
            o.sameCount == sameCount && isBonus == o.bonusCase
        ).findFirst().orElse(ELSE);
    }

    public String formatting(int sameCount){
        return String.format(winningCaseFormat,sameCount,winningMoney,sameCount);
    }

    public long calculateEarnMoney(int winningCaseCount) {
        return winningCaseCount * winningMoney;
    }
}
