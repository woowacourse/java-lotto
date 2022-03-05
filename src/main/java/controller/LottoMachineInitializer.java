package controller;

import dto.LottoDto;
import java.util.List;

import java.util.stream.Collectors;
import model.LottoMachine;
import view.InputView;

public class LottoMachineInitializer {

    private final InputView inputView;

    public LottoMachineInitializer(final InputView inputView) {
        this.inputView = inputView;
    }

    public LottoMachine initLottoMachine() {
        final LottoMachine lottoMachine = new LottoMachine();
        insertPurchaseMoneyAndManualLottoCountInLottoMachine(lottoMachine);
        insertManualLottoNumberGroupsInLottoMachine(lottoMachine);
        return lottoMachine;
    }

    private void insertPurchaseMoneyAndManualLottoCountInLottoMachine(final LottoMachine lottoMachine) {
        final int purchaseMoney = inputView.inputPurchaseMoney();
        final int manualLottoCount = inputView.inputManualLottoCount();

        lottoMachine.makeTotalLottoCountToPurchase(purchaseMoney, manualLottoCount);
    }

    private void insertManualLottoNumberGroupsInLottoMachine(final LottoMachine lottoMachine) {
        int manualLottoCount = lottoMachine.sendManualLottoCount();
        List<LottoDto> manualLottoNumberGroups = inputManualLottoNumberGroups(manualLottoCount);

        lottoMachine.purchaseLottos(manualLottoNumberGroups);
    }

    private List<LottoDto> inputManualLottoNumberGroups(final int manualLottoCount) {
        return convertToDto(inputView.inputManualLottoNumberGroups(manualLottoCount));
    }

    private List<LottoDto> convertToDto(final List<List<Integer>> lottoNumberGroups) {
        return lottoNumberGroups.stream()
                .map(LottoDto::new)
                .collect(Collectors.toList());
    }
}
