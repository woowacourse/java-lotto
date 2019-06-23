package lotto.dao.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;

public class NamedSqlMapper {
    private Map<Integer, String> indexMap;
    private String mappedSql;

    private NamedSqlMapper(Map<Integer, String> indexMap, String mappedSql) {
        this.indexMap = indexMap;
        this.mappedSql = mappedSql;
    }

    public static NamedSqlMapper mapSql(String sql, Map<String, ?> rowMapper) {
        StringBuilder mappedSql = new StringBuilder();
        Map<Integer, String> indexMap = new HashMap<>();
        int index = 1;
        for (int i = 0, len = sql.length(); i < len; i++) {
            char symbol = sql.charAt(i);
            if (symbol == ':') {
                StringBuilder name = new StringBuilder();
                for (i++; i < len; i++) {
                    if (sql.charAt(i) == ' ') {
                        break;
                    }
                    if (sql.charAt(i) == ';') {
                        mappedSql.append(";");
                        break;
                    }
                    name.append(sql.charAt(i));
                }
                indexMap.put(index++, name.toString());
                mappedSql.append(" ? ");
                continue;
            }
            mappedSql.append(symbol);
        }

        return new NamedSqlMapper(indexMap, mappedSql.toString());
    }

    public Map<Integer, String> getIndexMap() {
        return indexMap;
    }

    public String getMappedSql() {
        return mappedSql;
    }
}
