package lotto.service;

import lotto.dao.ResultDao;
import lotto.domain.BoughtLottos;
import lotto.domain.Prize;
import lotto.domain.Result;
import lotto.domain.WinningNumber;
import lotto.domain.generator.ResultGenerator;
import lotto.dto.ResultDto;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ResultService {

    ResultDao resultDao;

    public ResultService() {
        this.resultDao = ResultDao.getInstance();
    }

    public Result generateResult(int round, BoughtLottos boughtLottos,
                                 WinningNumber winningNumber) throws SQLException {
        Result result = ResultGenerator.generateResult(boughtLottos, winningNumber);
        ResultDto resultDto = generateResultDto(result);
        resultDao.addResult(round, resultDto);

        return result;
    }

    private ResultDto generateResultDto(Result result) {
        Map<String, Integer> prizeResult = new HashMap<>();
        int winningMoney = 0;

        for (Prize prize : Prize.values()) {
            String prizeName = prize.name();
            int countOfPrize = result.getCountOfPrize(prize);

            prizeResult.put(prizeName.toLowerCase(), countOfPrize);
            winningMoney += prize.getWinningAmount() * countOfPrize;
        }

        ResultDto resultDto = new ResultDto();
        resultDto.setPrize(prizeResult);
        resultDto.setWinningMoney(winningMoney);
        return resultDto;
    }
}
