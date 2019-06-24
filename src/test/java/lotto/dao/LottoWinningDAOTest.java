package lotto.dao;

import lotto.dto.ResultLottoDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoWinningDAOTest {

    private LottoWinningDAO winningDAO;

    @BeforeEach
    void setUp() {
        winningDAO = LottoWinningDAO.getInstance();
    }

    @Test
    void 결과_데이터_디비에_잘들어가는지() {
        ResultLottoDTO data = new ResultLottoDTO();
        data.setWinningLotto("1,2,3,4,5,6");
        data.setRank(Arrays.asList("3개 일치 (5000원)- 1개"));
        data.setPrize(5000);
        data.setIncomeRate(30);
        assertEquals(winningDAO.addResult(data), 1);
    }

    @Test
    void 결과_데이터_잘나오는지_테스트() {
        assertEquals(winningDAO.findByResultRound(2), Arrays.asList("1,2,3,4,5,6"
                , "[3개 일치 (5000원)- 1개]", "5000", "30"));
    }

    @AfterEach
    void tearDown() {
        winningDAO = null;
    }
}
