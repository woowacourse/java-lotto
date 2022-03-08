package service;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.Map;
import model.Budget;
import model.IssuedLottos;
import model.Lotto;
import model.LottoNumber;
import model.LottoRank;
import model.LottoResult;
import model.ManualLottoCount;
import model.WinningLottoNumbers;
import model.generator.ManualGenerator;
import model.generator.MergeGenerator;
import model.generator.RandomGenerator;
import service.dto.BudgetDto;
import service.dto.IssuedLottosDto;
import service.dto.LottoDto;
import service.dto.LottoResultDto;
import service.dto.ManualLottoCountDto;
import service.dto.ManualLottosRequest;
import service.dto.PurchasedLottosDto;
import service.dto.WinningLottoNumbersDto;

public class LottoService {
    private Budget budget;
    private IssuedLottos issuedLottos;

    public PurchasedLottosDto purchaseLottos(BudgetDto budgetDto, ManualLottoCountDto manualLottoCountDto,
                                             ManualLottosRequest manualLottosRequest) {
        budget = new Budget(budgetDto.getBudgetAmount());
        IssuedLottos autoLottos = issueAutoLottos(manualLottoCountDto);
        IssuedLottos manualLottos = issueManualLottos(manualLottosRequest);
        issuedLottos = IssuedLottos.generatedBy(new MergeGenerator(manualLottos, autoLottos));
        return new PurchasedLottosDto(convertToDto(issuedLottos), manualLottos.getLottosCount(),
                autoLottos.getLottosCount());
    }

    private IssuedLottos issueManualLottos(ManualLottosRequest manualLottosRequest) {
        IssuedLottos manualLottos = IssuedLottos.generatedBy(
                new ManualGenerator(manualLottosRequest.getManualLottos()));
        return manualLottos;
    }

    private IssuedLottos issueAutoLottos(ManualLottoCountDto manualLottoCountDto) {
        ManualLottoCount manualLottoCount = new ManualLottoCount(manualLottoCountDto.getCount(), budget);
        IssuedLottos autoLottos = IssuedLottos.generatedBy(
                new RandomGenerator(manualLottoCount.getAutoLottoCountFrom(budget)));
        return autoLottos;
    }

    private IssuedLottosDto convertToDto(IssuedLottos issuedLottos) {
        return issuedLottos.getLottos().stream()
                .map(this::convertToDto)
                .collect(collectingAndThen(toList(), IssuedLottosDto::new));
    }

    private LottoDto convertToDto(Lotto lotto) {
        return lotto.getLottoNumbers().stream()
                .map(LottoNumber::intValue)
                .collect(collectingAndThen(toList(), LottoDto::new));
    }

    public LottoResultDto matchLottos(WinningLottoNumbersDto winningLottoNumbersDto) {
        WinningLottoNumbers winningLottoNumbers = new WinningLottoNumbers(
                Lotto.of((winningLottoNumbersDto.getWinningLotto().getLottoNumbers())),
                LottoNumber.of(winningLottoNumbersDto.getBonusNumber()));
        LottoResult result = issuedLottos.summarize(winningLottoNumbers);
        return convertToDto(result);
    }

    private LottoResultDto convertToDto(LottoResult result) {
        Map<LottoRank, Integer> resultMap = result.getResultMap();
        Map<String, Integer> resultMapDto = resultMap.keySet().stream()
                .collect(toMap(rank -> rank.name(), rank -> resultMap.get(rank)));
        return new LottoResultDto(resultMapDto, result.getProfitRate(budget));
    }
}
