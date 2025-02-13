package controller;

import domain.Lotto;
import domain.LottoPrize;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputView {
    
    public void printInputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
    }
    
    public void printPurchase(int purchaseCount) {
        System.out.printf("%d개를 구매했습니다.\n", purchaseCount);
    }
    
    public void printLottos(List<Lotto> lottos) {
        StringBuilder sb = new StringBuilder();
        for (Lotto lotto : lottos) {
            sb.append(lotto).append("\n");
        }
        System.out.println(sb);
    }
    
    public void printMatchLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }
    
    public void printBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }
    
    public void printStaticsLotto(EnumMap<LottoPrize, Integer> staticsLottos) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append("당첨 통계").append("\n");
        sb.append("---------").append("\n");
        
        StringJoiner sj = new StringJoiner("\n");
        for (Map.Entry<LottoPrize, Integer> lottoPrize : staticsLottos.entrySet()) {
            
            LottoPrize prizeKey = lottoPrize.getKey();
            String format = getLottoPrizeFormat(prizeKey);
            String result = String.format(format,
                    prizeKey.getMinMatchCount(),
                    prizeKey.getPrizeMoney(),
                    lottoPrize.getValue());
            sj.add(result);
        }
        sb.append(sj);
        
        System.out.println(sb);
    }
    
    private static String getLottoPrizeFormat(LottoPrize lottoPrize) {
        StringBuilder prizeFormat = new StringBuilder();
        prizeFormat.append("%d개 일치");
        
        if (lottoPrize.getBonusMatch() != null && lottoPrize.getBonusMatch()) {
            prizeFormat.append(", 보너스 볼 일치");
        }
        prizeFormat.append(" (%d원)- %d개");
        
        return prizeFormat.toString();
    }
    
    public void printIncomeRate(double incomeRate) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("총 수익률은 %s입니다.");
        if (incomeRate < 1.0f) {
            sb.append("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }
        
        System.out.printf(sb + "\n", getFormattedIncomeRate(incomeRate));
    }
    
    private static String getFormattedIncomeRate(double incomeRate) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        return decimalFormat.format(incomeRate);
    }
}
