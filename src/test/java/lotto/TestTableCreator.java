package lotto;

import lotto.config.DBConnector;
import lotto.config.DataSource;
import lotto.config.TableCreator;

public class TestTableCreator {
    private static final DBConnector DB_CONNECTOR = new DBConnector(DataSource.getTestInstance());
    private static final TableCreator TABLE_CREATOR = new TableCreator(DB_CONNECTOR);


    private TestTableCreator() {
    }

    public static void create() {
        try {
            TABLE_CREATOR.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
