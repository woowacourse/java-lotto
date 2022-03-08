package controller;

import service.LottoService;
import service.dto.BudgetDto;
import service.dto.LottoResultDto;
import service.dto.ManualLottoCountDto;
import service.dto.ManualLottosRequest;
import service.dto.PurchasedLottosDto;
import service.dto.WinningLottoNumbersDto;

public class LottoController {

    private final LottoService service;

    public LottoController(LottoService service) {
        this.service = service;
    }

    public PurchasedLottosDto issueLottos(BudgetDto budgetDto, ManualLottoCountDto manualLottoCountDto,
                                          ManualLottosRequest manualLottosRequest) {
        return service.purchaseLottos(budgetDto, manualLottoCountDto, manualLottosRequest);
    }

    public LottoResultDto matchLottos(WinningLottoNumbersDto winningLottoNumbersDto) {
        return service.matchLottos(winningLottoNumbersDto);
    }
}
