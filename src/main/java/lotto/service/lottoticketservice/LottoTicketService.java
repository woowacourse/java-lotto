package lotto.service.lottoticketservice;

import lotto.dao.lotto.LottoTicketDAO;
import lotto.dto.LottoTicketDTO;
import lotto.model.customer.PurchaseAmount;
import lotto.model.lotto.LottoTickets;
import lotto.model.lottostore.LottoStore;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketService {
    public static List<LottoTicketDTO> getLottoTicketByLottoRoundId(int lottoRoundId) throws SQLException {
        return LottoTicketDAO.selectLottoTicketsByLottoRoundId(lottoRoundId);
    }

    public static void addLottoTicketByLottoRoundId(String[] manualLottoTickets, int amount, int lottoRoundId) throws SQLException {
        PurchaseAmount purchaseAmount = PurchaseAmount.from(amount);
        List<LottoTicketDTO> manualLottoTicketDTOs = Arrays.stream(manualLottoTickets)
                .map(LottoTicketDTO::new)
                .collect(Collectors.toList());

        LottoTickets manualLottoTicket = LottoTicketAssembler.toLottoTickets(manualLottoTicketDTOs);

        LottoTickets lottoTickets = LottoStore.buyLottoTickets(purchaseAmount, manualLottoTicket);
        lottoTickets.stream().forEach(lottoTicket -> {
            try {
                LottoTicketDAO.insertLottoTicket(LottoTicketAssembler.toDTO(lottoTicket), lottoRoundId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
