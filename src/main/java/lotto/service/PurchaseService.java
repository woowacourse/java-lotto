package lotto.service;

import lotto.database.ConnectionUtil;
import lotto.database.dao.LottoTicketDAO;
import lotto.domain.machine.Money;
import lotto.domain.machine.Purchase;
import lotto.domain.machine.VendingMachine;
import lotto.domain.ticket.LottoTickets;
import lotto.dto.PurchaseDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PurchaseService {
    public static PurchaseDTO createPurchaseDTO(int round, Money money, List<List<Integer>> manualNumbers) {
        VendingMachine vendingMachine = new VendingMachine(money);
        Purchase purchase = vendingMachine.createPurchase(manualNumbers.size(), manualNumbers);
        LottoTickets lottoTickets = vendingMachine.createLotto(purchase);
        return new PurchaseDTO(
                round,
                manualNumbers.size(),
                money.ticketQuantity() - manualNumbers.size(),
                lottoTickets
        );
    }

    public static void savePurchase(PurchaseDTO purchaseDTO) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        new LottoTicketDAO(connection).addLottoTickets(purchaseDTO);
    }


}

