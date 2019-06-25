package lotto.dao.JdbcTemplate;

import lotto.dao.winninglotto.sqls.WinningLottoDAOSQLs;

import java.util.HashMap;
import java.util.Map;

public class NamedSqlMapper {
    private static final Character COLON = ':';
    private static final Character SEMI_COLON = ';';
    private static final Character WHITE_SPACE = ' ';
    private static final String QUESTION_MARK = " ? ";

    private Map<Integer, String> indexMap;
    private String mappedSQL;

    private NamedSqlMapper(Map<Integer, String> indexMap, String mappedSQL) {
        this.indexMap = indexMap;
        this.mappedSQL = mappedSQL;
    }

    public static NamedSqlMapper mapSQL(String sql) {
        StringBuilder mappedSQL = new StringBuilder();
        Map<Integer, String> indexMap = new HashMap<>();
        int index = 1;
        for (int i = 0, len = sql.length(); i < len; i++) {
            char symbol = sql.charAt(i);
            if (COLON.equals(symbol)) {
                int nameEndIndex = extractNameIndex(sql, ++i, len);
                String name = sql.substring(i, nameEndIndex);
                i = nameEndIndex;
                indexMap.put(index++, name);
                mappedSQL.append(QUESTION_MARK);
                continue;
            }
            mappedSQL.append(symbol);
        }

        return new NamedSqlMapper(indexMap, mappedSQL.toString());
    }

    private static int extractNameIndex(String sql, int i, int len) {
        for (; i < len; i++) {
            if (WHITE_SPACE.equals(sql.charAt(i))) {
                break;
            }
            if (SEMI_COLON.equals(sql.charAt(i))) {
                i--;
                break;
            }
        }
        return i;
    }

    public Map<Integer, String> getIndexMap() {
        return indexMap;
    }

    public String getMappedSQL() {
        return mappedSQL;
    }
}
