package lotto.model;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WinStat {
    private static final DecimalFormat PERCENT
            = new DecimalFormat("#.#%"); // 소수점 아래 1자리까지 퍼센트
    private static final String EMPTY = "";
    private static final String NEW_LINE = "\n";
    private static final String STATICS_TITLE = "당첨 통계";
    private static final String HORIZONTAL_BAR = "----------";
    private static final String ITEM_DELIMITER = " - ";
    private static final String QUANTITY = "개";
    private static final String TOTAL_PROFIT_RATE = "총 수익률은 ";
    private static final String SENTENCE_LAST = "입니다.";
    private static final String HEADER
            = STATICS_TITLE + NEW_LINE + HORIZONTAL_BAR + NEW_LINE;

    private final Map<Rank, Integer> stat = new LinkedHashMap<>();
    private final List<Lotto> lottoList;
    private final LottoRule rule;

    public WinStat(final List<Lotto> lottoList, final WinningLotto winLotto, final LottoRule rule) {
        this.lottoList = lottoList;
        this.rule = rule;
        for (Rank rank : Rank.values()) { // 자료구조 초기화
            stat.put(rank, 0);
        }
        for (Lotto lotto : lottoList) {
            Rank key = winLotto.match(lotto);
            stat.put(key, stat.get(key) + 1);
        }
    }

    /**
     * 총 당첨금 계산
     */
    private int getTotalPrizeMoney() {
        int result = 0;
        for (Map.Entry<Rank, Integer> entry : stat.entrySet()) {
            Rank key = entry.getKey();
            int value = entry.getValue();
            result += (key.getWinningMoney() * value);
        }
        return result;
    }

    /**
     * 총 수익률 메시지를 생성.
     */
    private String getProfitRateMessage() {
        double rate
                = (double) getTotalPrizeMoney() // 소수점 형변환
                / (lottoList.size() * rule.getPrice());
        if (Double.isNaN(rate)) { // 0으로 나뉠 경우 대비
            rate = 0;
        }
        return TOTAL_PROFIT_RATE + PERCENT.format(rate) + SENTENCE_LAST;
    }

    public String makeString() {
        final StringBuilder result = new StringBuilder(HEADER);
        for (Map.Entry<Rank, Integer> entry : stat.entrySet()) {
            Rank key = entry.getKey();
            int value = entry.getValue();
            String temp
                    = key.equals(Rank.MISS)
                    ? EMPTY // 낙첨은 당첨 통계에서 출력하지 않도록 함
                    : key.getRankDescription() + ITEM_DELIMITER + value + QUANTITY + NEW_LINE;
            result.append(temp);
        }
        result.append(getProfitRateMessage());
        return result.toString();
    }
}
