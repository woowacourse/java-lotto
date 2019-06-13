package lotto.service;

import lotto.dao.LottoDao;
import lotto.dao.LottoGameDao;
import lotto.domain.*;
import lotto.domain.generator.ResultGenerator;
import lotto.dto.LottoDto;
import lotto.dto.LottoGameDto;
import lotto.dto.ResultDto;
import lotto.dto.WinningNumberDto;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static lotto.domain.generator.LottoNumbersGenerator.generateLottoNumbers;

public class LottoService {

    private LottoGameDao lottoGameDao;
    private LottoDao lottoDao;

    public LottoService() {
        lottoGameDao = new LottoGameDao();
        lottoDao = new LottoDao();
    }

    public BoughtLottos generateBoughtLottos(int round, String inputMoney, String inputLottos) throws SQLException {
        Money money = new Money(Integer.parseInt(inputMoney));
        List<String> inputManualLottos = processedInputManualLottos(inputLottos);

        LottoGameDto lottoGameDto = generateLottoGameDto(round, money.getBuyPrice(), inputManualLottos.size());
        lottoGameDao.addRound(lottoGameDto);

        BoughtLottos boughtLottos = BoughtLottos.buyLottos(money, inputManualLottos);
        lottoDao.addAllLotto(convertLottoDtoFrom(boughtLottos.getLottos()), round);
        return boughtLottos;
    }

    private List<String> processedInputManualLottos(String inputManualLottos) {
        if (inputManualLottos.equals("")) {
            return Collections.EMPTY_LIST;
        }

        return Arrays.stream(inputManualLottos.split("\n"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private LottoGameDto generateLottoGameDto(int round, int money, int countOfManual) {
        LottoGameDto lottoGameDto = new LottoGameDto();
        lottoGameDto.setRound(round);
        lottoGameDto.setMoney(money);
        lottoGameDto.setCountOfManual(countOfManual);

        return lottoGameDto;
    }

    private List<LottoDto> convertLottoDtoFrom(List<Lotto> lottos) {
        List<LottoDto> lottoDtos = new ArrayList<>();

        for (Lotto lotto : lottos) {
            List<Integer> lottoNumbers = convertIntegerFrom(lotto.getNumbers());
            lottoDtos.add(new LottoDto(lottoNumbers));
        }

        return lottoDtos;
    }

    private List<Integer> convertIntegerFrom(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());
    }

    public WinningNumber generateWinningNumber(int round, String winningNumbers, String bonus) throws SQLException {
        List<LottoNumber> lottoNumbers = generateLottoNumbers(winningNumbers);
        int bonusBall = Integer.parseInt(bonus);

        WinningNumberDto winningNumberDto = generateWinningNumberDto(convertIntegerFrom(lottoNumbers), bonusBall);
        lottoGameDao.addWinningNumber(round, winningNumberDto);

        return new WinningNumber(new Lotto(lottoNumbers), bonusBall);
    }

    private WinningNumberDto generateWinningNumberDto(List<Integer> winningNumbers, int bonusBall) {
        WinningNumberDto winningNumberDto = new WinningNumberDto();
        winningNumberDto.setNumbers(winningNumbers);
        winningNumberDto.setBonusBall(bonusBall);

        return winningNumberDto;
    }

    public Result generateResult(int round, BoughtLottos boughtLottos,
                                 WinningNumber winningNumber) throws SQLException {
        Result result = ResultGenerator.generateResult(boughtLottos, winningNumber);
        ResultDto resultDto = generateResultDto(result);
        lottoGameDao.addResult(round, resultDto);

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
