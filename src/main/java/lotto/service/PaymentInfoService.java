package lotto.service;

import lotto.dao.PaymentInfoDao;
import lotto.domain.dto.PaymentInfoDto;
import lotto.domain.paymentinfo.CountOfLotto;
import lotto.domain.paymentinfo.Payment;

import java.sql.SQLDataException;

public class PaymentInfoService {
    private PaymentInfoService() {
    }

    private static class PaymentServiceHolder {
        private static final PaymentInfoService INSTANCE = new PaymentInfoService();
    }

    public static PaymentInfoService getInstance() {
        return PaymentServiceHolder.INSTANCE;
    }

    public int insertUser(String userName) throws SQLDataException {
        return PaymentInfoDao.getInstance().insertUser(userName);
    }

    public int insertPaymentInfoAndReturnKeyValue(Payment payment, CountOfLotto countOfLotto, String name) throws SQLDataException {
        PaymentInfoDto paymentInfoDTO = createPaymentInfoDTO(payment, countOfLotto, name);
        return PaymentInfoDao.getInstance().insertPayment(paymentInfoDTO);
    }

    private static PaymentInfoDto createPaymentInfoDTO(Payment payment, CountOfLotto countOfLotto, String name) {
        PaymentInfoDto paymentInfoDTO = new PaymentInfoDto();
        paymentInfoDTO.setPayment(payment.getPayment());
        paymentInfoDTO.setManual(countOfLotto.getCountOfManualLotto());
        paymentInfoDTO.setAuto(countOfLotto.getCountOfRandomLotto());
        paymentInfoDTO.setName(name);
        return paymentInfoDTO;
    }
}
