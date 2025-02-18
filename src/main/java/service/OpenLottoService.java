package service;

import constant.WinningCount;
import domain.WinningLotto;
import dto.IssuedLottosDto;
import dto.WinningLottoDto;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class OpenLottoService {


    public WinningLottoDto makeWinningLottoDto(List<Integer> numbers, Integer bonusNumber) {
        return new WinningLottoDto(numbers, bonusNumber);
    }

    public Map<WinningCount, Integer> openResult(WinningLottoDto winningLottoDto, IssuedLottosDto issuedLottosDto) {
        WinningLotto winningLotto = new WinningLotto(winningLottoDto.numbers(), winningLottoDto.bonusNumber());
        Map<WinningCount, Integer> result = new EnumMap<>(WinningCount.class);
        issuedLottosDto.lottos().forEach(lotto -> {
            WinningCount lottoResult = winningLotto.getLottoResult(lotto.numbers());
            result.put(lottoResult, result.getOrDefault(lottoResult, 0) + 1);
        });
        return result;
    }

    public Double calculateEarningRate(Map<WinningCount, Integer> result, int cost) {
        double sum = 0;
        for (WinningCount winningCount : result.keySet()) {
            sum += result.getOrDefault(winningCount, 0) * winningCount.getAmount();
        }
        return Math.round((sum * 100) / cost) / 100.0;
    }
}
