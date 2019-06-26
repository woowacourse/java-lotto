package lotto.view;

import lotto.controller.LottoGame;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Rank;
import lotto.model.WinStat;

import java.util.*;

public class JsonOutput {
    private static final String BRACE_OPEN = "{";
    private static final String BRACE_CLOSE = "}";
    private static final String SQUARE_BRACKET_OPEN = "[";
    private static final String SQUARE_BRACKET_CLOSE = "]";
    private static final String COLON_DELIMITER = ": ";
    private static final String COMMA_SEPARATOR = ", ";
    private static final String DOUBLE_QUOTE = "\"";
    private static final String QUERY_STATUS = "queryStatus";
    private static final String STATUS_OK = "ok";
    private static final String STATUS_FAILED = "failed";
    private static final String ERROR = "error";
    private static final Map<Rank, String> RANK_MAP = new HashMap<>();

    static {
        RANK_MAP.put(Rank.FIRST, "1");
        RANK_MAP.put(Rank.SECOND, "2");
        RANK_MAP.put(Rank.THIRD, "3");
        RANK_MAP.put(Rank.FOURTH, "4");
        RANK_MAP.put(Rank.FIFTH, "5");
        RANK_MAP.put(Rank.MISS, "0");
    }

    public static String responseOk() {
        return braceWrap(keyAndStringValue(QUERY_STATUS, STATUS_OK));
    }

    private static String responseOk(final String keyName, final String value) {
        return braceWrap(
                keyAndStringValue(QUERY_STATUS, STATUS_OK)
                        + COMMA_SEPARATOR
                        + keyAndJsonValue(keyName, value)
        );
    }

    public static String responseFailed(final String errorMessage) {
        return braceWrap(
                keyAndStringValue(QUERY_STATUS, STATUS_FAILED)
                        + COMMA_SEPARATOR
                        + keyAndStringValue(ERROR, errorMessage)
        );
    }

    private static String quotationWrap(final String string) {
        return DOUBLE_QUOTE + string + DOUBLE_QUOTE;
    }

    private static String braceWrap(final String string) {
        return BRACE_OPEN + string + BRACE_CLOSE;
    }

    private static String squareBracketWrap(final String string) {
        return SQUARE_BRACKET_OPEN + string + SQUARE_BRACKET_CLOSE;
    }

    private static String keyAndJsonValue(final String key, final String value) {
        return quotationWrap(key) + COLON_DELIMITER + value;
    }

    private static String keyAndStringValue(final String key, final String value) {
        return keyAndJsonValue(key, quotationWrap(value));
    }

    private static String keyAndIntValue(final String key, final int value) {
        return quotationWrap(key) + COLON_DELIMITER + value;
    }

    private static String keyAndDoubleValue(final String key, final double value) {
        return quotationWrap(key) + COLON_DELIMITER + value;
    }

    private static String joinByComma(final String... strings) {
        return String.join(COMMA_SEPARATOR, strings);
    }

    private static String statToJson(final WinStat stat) {
        final List<String> result = new ArrayList<>();
        final Iterator<Map.Entry<Rank, Integer>> iterator = stat.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Rank, Integer> entry = iterator.next();
            Rank key = entry.getKey();
            int value = entry.getValue();
            result.add(keyAndIntValue(RANK_MAP.get(key), value));
        }
        return braceWrap(String.join(COMMA_SEPARATOR, result));
    }

    private static String lottoToJson(final Lotto lotto) {
        List<String> result = new ArrayList<>();
        for (int i : lotto.getNumbers()) {
            result.add(Integer.toString(i));
        }
        return squareBracketWrap(String.join(COMMA_SEPARATOR, result));
    }

    private static String lottosToJson(final Lottos lottos) {
        List<String> result = new ArrayList<>();
        for (Lotto lotto : lottos) {
            result.add(lottoToJson(lotto));
        }
        return squareBracketWrap(String.join(COMMA_SEPARATOR, result));
    }

    public static String lottoGameToJson(final LottoGame game) {
        final String result = joinByComma(
                keyAndIntValue("allPurchaseAmount", game.getAllPurchaseCount() * game.getPrice()),
                keyAndIntValue("autoPurchaseCount", game.getAutoPurchaseCount()),
                keyAndIntValue("manualPurchaseCount", game.getManualPurchaseCount()),
                keyAndJsonValue("lottos", lottosToJson(game.getLottos())),
                keyAndJsonValue("stat", statToJson(game.getStat())),
                keyAndIntValue("totalPrizeMoney", game.getStat().getTotalPrizeMoney()),
                keyAndDoubleValue("profitRate", game.getStat().getProfitRate())
        );
        return responseOk("result", braceWrap(result));
    }
}
