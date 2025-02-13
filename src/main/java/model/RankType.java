package model;

public enum RankType {


    FIRST(6,2000000000,0),
    SECOND(5,30000000,0),
    THIRD(5,1500000,0),
    FOURTH(4,50000,0),
    FIFTH(3,5000,0);

    RankType(int winningCount, int price, int count) {
        this.winningCount = winningCount;
        this.price = price;
        this.count = count;
    }

    private static final String PRINT_FORMAT = "%d개 일치, (%d원)- %d개\n";
    private static final String PRINT_FORMAT_SECOND = "%d개 일치, 보너스 볼 일치 (%d원)- %d개\n";
    private int winningCount;
    private int price;
    private int count;

    public static void saveGameResult(int matchCount, boolean bonusBall) {
        for (RankType rankType : RankType.values()) {
            if (rankType.winningCount == matchCount) {
                increaseCount(rankType,bonusBall);
            }
        }
    }

    public static void increaseCount(RankType rankType, boolean bonusBall) {
        if (rankType.winningCount == 5 && RankType.SECOND == rankType) {
            if (bonusBall) {
                rankType.count ++;
                return;
            }
        }
        if (rankType.winningCount == 5 && RankType.THIRD == rankType) {
            if (!bonusBall) {
                rankType.count ++;
                return;
            }
        }
        rankType.count ++;
    }

    public static String makeLottoResult(){
        StringBuilder stringBuilder = new StringBuilder();

        for(RankType rankType : RankType.values()){
            if(rankType == RankType.SECOND){
                stringBuilder.append(String.format(PRINT_FORMAT, rankType.winningCount, rankType.price, rankType.count));
                continue;
            }
            stringBuilder.append(String.format(PRINT_FORMAT, rankType.winningCount, rankType.price, rankType.count));
        }

        return stringBuilder.toString();
    }

    public static int calculateTotalPrice(){
        int totalPrice = 0;

        for(RankType rankType : RankType.values()){
            totalPrice += rankType.price * rankType.count;
        }

        return totalPrice;
    }
}
