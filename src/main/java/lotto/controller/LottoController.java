package lotto.controller;

import lotto.domain.LottoMachine;
import lotto.domain.lottonumber.Lotto;
import lotto.domain.matchkind.LottoMatchKind;
import lotto.domain.winningresult.WinningResult;
import lotto.dto.InputLottoDto;
import lotto.dto.LottoNumbersDto;
import lotto.dto.WinningKindDto;
import lotto.util.converter.NumberConverter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoController {
    private LottoMachine lottoMachine;
    private final LottoMachine.Builder lottoMachineBuilder = new LottoMachine.Builder();

    public void setPurchaseAmount(final int purchaseAmount) {
        lottoMachineBuilder.setTotalPurchaseAmount(purchaseAmount);
    }

    public void setManualLottoNumbers(final List<String> manualLottoNumbers) {
        final List<InputLottoDto> lottos = splitLottos(manualLottoNumbers);
        lottoMachineBuilder.setManualLottos(lottos);
        lottoMachine = lottoMachineBuilder.build();
    }

    private List<InputLottoDto> splitLottos(final List<String> lottoNumbers) {
        return lottoNumbers.stream()
                .map(lottoInfo -> lottoInfo.split(","))
                .map(this::convertToInputLottoDtos)
                .map(InputLottoDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Integer> convertToInputLottoDtos(final String[] lottoInfo) {
        return Arrays.stream(lottoInfo)
                .map(String::trim)
                .map(NumberConverter::convertStringToInt)
                .collect(Collectors.toUnmodifiableList());
    }

    public int getAutoPurchaseCount() {
        return lottoMachine.getCountOfAutoLottoNumbers();
    }

    public int getManualPurchaseCount() {
        return lottoMachine.getCountOfManualLottoNumbers();
    }

    public List<LottoNumbersDto> getLottos() {
        final List<Lotto> lottos = lottoMachine.getLottos();
        return lottos.stream()
                .map(Lotto::getValues)
                .map(LottoNumbersDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<WinningKindDto> getWinningResult(final List<Integer> winningLotto, final int bonusNumber) {
        final WinningResult winningResult = lottoMachine.getMatchResult(winningLotto, bonusNumber);
        final Map<LottoMatchKind, Integer> winningNumberByKind = winningResult.getWinningNumberByKind();
        return convertToDto(winningNumberByKind);
    }

    private List<WinningKindDto> convertToDto(final Map<LottoMatchKind, Integer> winningNumberByKind) {
        return winningNumberByKind.keySet().stream()
                .map(winningKind -> new WinningKindDto(winningKind, winningNumberByKind.get(winningKind)))
                .collect(Collectors.toUnmodifiableList());
    }

    public double getProfitRate(final List<Integer> winningLotto, final int bonusNumber) {
        final WinningResult winningResult = lottoMachine.getMatchResult(winningLotto, bonusNumber);
        return winningResult.getProfitRate();
    }
}
