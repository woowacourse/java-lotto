package view;

import domain.Amount;
import domain.dto.GetLottoDto;
import domain.dto.GetLottosDto;

public class OutputView {

    public void printAmount(Amount amount) {
        System.out.printf(Output.PURCHASE_MESSAGE.getMessage(), amount.getAmount());
    }

    public void printLottos(GetLottosDto lottosDto) {
        System.out.println();
        lottosDto.getLottoDtos().forEach(this::printLotto);
    }

    private void printLotto(final GetLottoDto getLottoDto) {
        System.out.println(getLottoDto.numbers());
    }

}

