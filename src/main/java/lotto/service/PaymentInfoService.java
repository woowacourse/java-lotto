package lotto.service;

import lotto.dao.PaymentInfoDao;
import lotto.service.dto.PaymentInfoDto;
import lotto.domain.paymentinfo.CountOfLotto;
import lotto.domain.paymentinfo.Payment;

import java.sql.SQLDataException;

import static lotto.controller.common.CommonController.nullable;

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

    public int insertPaymentInfoAndReturnKeyValue(int inputPayment, int countOfManualLotto, String name) throws SQLDataException {
        Payment payment = new Payment(inputPayment);
        CountOfLotto countOfLotto = new CountOfLotto(payment, countOfManualLotto);

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
