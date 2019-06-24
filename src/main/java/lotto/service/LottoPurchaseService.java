package lotto.service;

import lotto.dao.DBUtil;
import lotto.dao.LottoRoundDAO;
import lotto.dao.LottoTicketDAO;
import lotto.domain.LottoMachine;
import lotto.domain.LottoQuantity;
import lotto.domain.lotto.LottoTicketGroup;
import lotto.domain.purchaseamount.PurchaseAmount;
import lotto.dto.PurchaseInformationDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoPurchaseService {
    public static PurchaseInformationDTO purchaseLottos(int round, String purchaseAmountText, String[] manualNums) throws SQLException {
        List<String> manualNumberText = (manualNums == null) ? new ArrayList<>() : Arrays.asList(manualNums);
        PurchaseAmount purchaseAmount = PurchaseAmount.createLottoPurchaseAmount(purchaseAmountText);

        LottoQuantity manualQuantity = LottoQuantity.create(manualNumberText.size(), purchaseAmount);
        LottoQuantity autoQuantity = purchaseAmount.maxLottoQuantity().subtract(manualQuantity);

        LottoTicketGroup totalLottos = generateLottos(manualNumberText, autoQuantity);
        saveLottos(round, totalLottos);

        return new PurchaseInformationDTO(
                manualQuantity.getQuantity(),
                autoQuantity.getQuantity(),
                totalLottos,
                purchaseAmount.buy(totalLottos.price()).getMoney()
        );
    }

    private static LottoTicketGroup generateLottos(List<String> manualNumberText, LottoQuantity autoQuantity) {
        LottoTicketGroup manualLottos = LottoMachine.generateManualLottos(manualNumberText);
        LottoTicketGroup autoLottos = LottoMachine.generateAutoLottos(autoQuantity);
        return manualLottos.combine(autoLottos);
    }

    private static void saveLottos(int round, LottoTicketGroup lottos) throws SQLException {
        Connection connection = DBUtil.getConnection();
        LottoRoundDAO.getInstance(connection).insertRound(round);
        LottoTicketDAO.getInstance(connection).insertLottoTickets(round, lottos);
    }
}
