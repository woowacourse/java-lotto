package lotto.service;

import lotto.dao.LottoDao;
import lotto.dao.LottoGameDao;
import lotto.domain.*;
import lotto.dto.LottoDto;
import lotto.dto.LottoGameDto;
import lotto.dto.WinningNumberDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.domain.generator.LottoNumbersGenerator.generateLottoNumbers;

public class LottoService {

    private LottoGameDao lottoGameDao;
    private LottoDao lottoDao;

    public LottoService() {
        lottoGameDao = LottoGameDao.getInstance();
        lottoDao = LottoDao.getInstance();
    }

    public int getCurrentRound() throws SQLException {
        return lottoGameDao.currentRound();
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
}
