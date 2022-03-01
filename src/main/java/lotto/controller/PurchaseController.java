package lotto.controller;

import java.util.List;

import lotto.dto.LottoTicketResponse;

public interface PurchaseController {
    List<LottoTicketResponse> purchase(String inputMoney);
}
