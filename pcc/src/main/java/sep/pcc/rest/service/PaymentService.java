package sep.pcc.rest.service;

import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import sep.pcc.rest.model.Payment;

@Service("paymentService")
@Transactional
public class PaymentService extends GenericServiceImpl<Payment>{
	
	

}
