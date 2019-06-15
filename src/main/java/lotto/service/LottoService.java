package lotto.service;

import lotto.dao.LottoDao;
import lotto.dao.LottoGameDao;
import lotto.dao.ResultDao;
import lotto.dao.WinningNumberDao;
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
    private ResultDao resultDao;
    private WinningNumberDao winningNumberDao;

    public LottoService() {
        lottoGameDao = LottoGameDao.getInstance();
        lottoDao = LottoDao.getInstance();
        resultDao = ResultDao.getInstance();
        winningNumberDao = WinningNumberDao.getInstance();
    }

    public int getCurrentRound() throws SQLException {
        return lottoGameDao.currentRound();
    }

    public BoughtLottos generateBoughtLottos(int round, String inputMoney, String inputLottos) throws SQLException {
        Money money = new Money(Integer.parseInt(inputMoney));
        List<String> inputManualLottos = processedInputManualLottos(inputLottos);

        LottoGameDto lottoGameDto = new LottoGameDto(round, money.getBuyPrice(), inputManualLottos.size());
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

        WinningNumberDto winningNumberDto = new WinningNumberDto(convertIntegerFrom(lottoNumbers), bonusBall);
        winningNumberDao.addWinningNumber(round, winningNumberDto);

        return winningNumberDto.toEntity();
    }

    public void generateResult(int round, BoughtLottos boughtLottos,
                               WinningNumber winningNumber) throws SQLException {
        Result result = ResultGenerator.generateResult(boughtLottos, winningNumber);
        ResultDto resultDto = generateResultDto(result);
        resultDao.addResult(round, resultDto);
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

        return new ResultDto(prizeResult, winningMoney);
    }
}
