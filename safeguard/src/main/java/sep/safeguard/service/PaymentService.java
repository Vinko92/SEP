package sep.safeguard.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import sep.safeguard.model.Payment;

@Service("paymentService")
public class PaymentService extends GenericServiceImpl<Payment> {

}
