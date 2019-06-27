package lotto.domain.dao;

import lotto.domain.dto.ResultDTO;

public interface ResultDAO {

    static ResultDAOImpl getInstance() {
        return ResultDAOImpl.getInstance();
    }

    void setResult(final ResultDTO resultDTO);
    ResultDTO getResult(final int newRound);
    void deleteResult(final int round);
}
