package lotto.dao;

public class ResultDao {
    private static final ResultDao INSTANCE = new ResultDao();

    private ResultDao() {

    }

    public static ResultDao getInstance() {
        return INSTANCE;
    }


}
