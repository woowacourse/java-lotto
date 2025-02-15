package model;

import java.util.LinkedHashMap;
import java.util.Map;

public enum RankType {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000);

    RankType(int winningCount, int price) {
        this.winningCount = winningCount;
        this.price = price;
    }

    private static final String PRINT_FORMAT = "%d개 일치, (%d원)- %d개\n";
    private static final String PRINT_FORMAT_SECOND = "%d개 일치, 보너스 볼 일치 (%d원)- %d개\n";
    private int winningCount;
    private int price;

    public static Map<RankType, Integer> makeMap() {
        Map<RankType, Integer> map = new LinkedHashMap<>();
        for(RankType rankType : RankType.values()){
            map.putIfAbsent(rankType, 0);
        }
        return map;
    }

    public static void updateMapByWinningCount(Map<RankType,Integer> map, int matchCount, boolean bonusBall) {
        for (RankType rankTypeTemp : map.keySet()) {
            if (rankTypeTemp.winningCount == matchCount) {
                updateMap(map, bonusBall, rankTypeTemp);
            }
        }
    }

    private static void updateMap(Map<RankType, Integer> map, boolean bonusBall, RankType rankTypeTemp) {
        if (rankTypeTemp.winningCount == 5) {
            checkSecondAndThird(rankTypeTemp, map, bonusBall);
            return;
        }
        map.put(rankTypeTemp, map.get(rankTypeTemp) + 1);
    }

    private static void checkSecondAndThird(RankType rankTypeTemp, Map<RankType, Integer> map, boolean bonusBall){
        if (rankTypeTemp == RankType.SECOND && bonusBall) {
            map.put(rankTypeTemp, map.get(rankTypeTemp) + 1);
        }
        if (rankTypeTemp == RankType.THIRD && !bonusBall) {
            map.put(rankTypeTemp,map.get(rankTypeTemp) + 1);
        }
    }

    public static String makeLottoResult(Map<RankType, Integer> map){
        StringBuilder stringBuilder = new StringBuilder();

        for(RankType rankType : map.keySet()){
            if(rankType == RankType.SECOND){
                stringBuilder.append(String.format(PRINT_FORMAT_SECOND, rankType.winningCount, rankType.price, map.get(rankType)));
                continue;
            }
            stringBuilder.append(String.format(PRINT_FORMAT, rankType.winningCount, rankType.price, map.get(rankType)));
        }

        return stringBuilder.toString();
    }

    public static int calculateTotalPrice(Map<RankType, Integer> map){
        int totalPrice = 0;

        for(RankType rankType : map.keySet()){
            totalPrice += rankType.price * map.get(rankType);
        }

        return totalPrice;
    }
}
