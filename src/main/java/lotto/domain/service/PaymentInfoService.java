package lotto.domain.service;

import lotto.domain.dao.PaymentInfoDao;
import lotto.domain.dto.PaymentInfoDTO;
import lotto.domain.paymentinfo.CountOfLotto;
import lotto.domain.paymentinfo.Payment;

import java.sql.SQLDataException;

public class PaymentInfoService {
    private PaymentInfoService() {
    }

    public static class PaymentServiceHolder{
        private static final PaymentInfoService INSTANCE = new PaymentInfoService();
    }

    public static PaymentInfoService getInstance() {
        return PaymentServiceHolder.INSTANCE;
    }

    public int insertUser(String userName) throws SQLDataException {
        return PaymentInfoDao.getInstance().insertUser(userName);
    }

    public int insertPaymentInfoAndReturnKeyValue(Payment payment, CountOfLotto countOfLotto, String name) throws SQLDataException {
        PaymentInfoDTO paymentInfoDTO = createPaymentInfoDTO(payment, countOfLotto, name);
        return PaymentInfoDao.getInstance().insertPayment(paymentInfoDTO);
    }

    private static PaymentInfoDTO createPaymentInfoDTO(Payment payment, CountOfLotto countOfLotto, String name) {
        PaymentInfoDTO paymentInfoDTO = new PaymentInfoDTO();
        paymentInfoDTO.setPayment(payment.getPayment());
        paymentInfoDTO.setManual(countOfLotto.getCountOfManualLotto());
        paymentInfoDTO.setAuto(countOfLotto.getCountOfRandomLotto());
        paymentInfoDTO.setName(name);
        return paymentInfoDTO;
    }
}
