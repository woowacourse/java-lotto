package controller;

import controller.dto.LottosDto;
import domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class LottoController {
    private static final String DELIMITER = ", ";

    public LottosDto purchase(int inputMoney, int manualLottoCount, List<String> manualLottoNumbers) {
        Money money = new Money(inputMoney);
        int autoLottoCount = money.getAutoLottoCount(manualLottoCount);
        Lottos manualLottos = createManualLottos(manualLottoNumbers);
        return new LottosDto(autoLottoCount, manualLottos);
    }

    public LottosDto createLottos(LottosDto lottosDto) {
        Lottos autoLottos = Lottos.generateAutoLottos(lottosDto.getAutoLottoCount());
        Lottos totalLottos = lottosDto.getLottos().concat(autoLottos);
        return new LottosDto(lottosDto.getAutoLottoCount(), totalLottos);
    }

    public Statistic winningResult(String inputWinningNumber, int inputBonusBall, LottosDto lottosDto) {
        Lotto winningNumber = generateManualLotto(inputWinningNumber);
        LottoNumber bonusBall = LottoNumber.of(inputBonusBall);
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusBall);
        return lottosDto.getLottos().getWinningStatistics(winningLotto);
    }


    private Lottos createManualLottos(List<String> manualLottoNumbers) {
        List<Lotto> lottos = manualLottoNumbers.stream()
                .map(LottoController::generateManualLotto)
                .collect(Collectors.toList());
        return new Lottos(lottos);
    }

    private static Lotto generateManualLotto(String input) {
        String[] numbers = input.split(DELIMITER);
        LottoGenerator lottoGenerator = new ManualLottoGenerator(numbers);
        return lottoGenerator.generateLotto();
    }
}
