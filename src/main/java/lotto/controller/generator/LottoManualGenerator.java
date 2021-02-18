package lotto.controller.generator;

import java.util.List;
import java.util.Scanner;
import lotto.viewer.LottoGeneratorInputView;

public class LottoManualGenerator implements LottoGenerator {

    private final LottoGeneratorInputView lottoGeneratorInputView;
    final Scanner scanner;

    public LottoManualGenerator(Scanner scanner) {
        this.lottoGeneratorInputView = new LottoGeneratorInputView(scanner);
        this.scanner = scanner;
    }

    @Override
    public List<Integer> generateNumbers() {
        return this.lottoGeneratorInputView.inputWinningNumbers();
    }
}
