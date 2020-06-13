package com.bookstore.resource;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.config.PaypalPaymentIntent;
import com.bookstore.config.PaypalPaymentMethod;
import com.bookstore.domain.User;
import com.bookstore.domain.UserBilling;
import com.bookstore.domain.UserPayment;
import com.bookstore.service.UserPaymentService;
import com.bookstore.service.UserService;
import com.bookstore.service.impl.PaypalService;
import com.bookstore.utility.Utils;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@RestController
@RequestMapping("/payment")
public class PaymentResource {

	@Autowired
	private UserService userService;

	@Autowired
	private UserPaymentService userPaymentService;

	@Autowired
	private PaypalService paypalService;

	public static final String URL_PAYPAL_SUCCESS = "checkout";
	public static final String URL_PAYPAL_CANCEL = "checkout?cancel=true";

	private Logger log = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> addNewCreditCardPost(@RequestBody UserPayment userPayment, Principal principal) {
		User user = userService.findByUsername(principal.getName());

		UserBilling userBilling = userPayment.getUserBilling();

		userService.updateUserBilling(userBilling, userPayment, user);

		return new ResponseEntity<String>("Payment Added(Updated) Successfully!", HttpStatus.OK);
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public ResponseEntity<String> removePaymentPost(@RequestBody String id, Principal principal) {

		userPaymentService.removeById(Long.valueOf(id));

		return new ResponseEntity<String>("Payment Removed Successfully!", HttpStatus.OK);
	}

	@RequestMapping(value = "/setDefault", method = RequestMethod.POST)
	public ResponseEntity<String> setDefaultPaymentPost(@RequestBody String id, Principal principal) {
		User user = userService.findByUsername(principal.getName());

		userService.setUserDefaultPayment(Long.parseLong(id), user);

		return new ResponseEntity<String>("Payment Removed Successfully!", HttpStatus.OK);
	}

	@RequestMapping("/getUserPaymentList")
	public List<UserPayment> getUserPaymentList(Principal principal) {
		User user = userService.findByUsername(principal.getName());

		List<UserPayment> userPaymentList = user.getUserPaymentList();

		return userPaymentList;
	}

	@PostMapping("/paypal")
	public ResponseEntity<String> pay(HttpServletRequest request, @RequestBody double price) {
		String cancelUrl = Utils.getFrontendBaseUrl() + "/" + URL_PAYPAL_CANCEL;
		String successUrl = Utils.getFrontendBaseUrl() + "/" + URL_PAYPAL_SUCCESS;
		try {
			Payment payment = paypalService.createPayment(price, "USD", PaypalPaymentMethod.paypal,
					PaypalPaymentIntent.sale, "payment description", cancelUrl, successUrl);
			for (Links links : payment.getLinks()) {
				if (links.getRel().equals("approval_url")) {
					return new ResponseEntity<String>(links.getHref(), HttpStatus.OK);
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return new ResponseEntity<String>("Payment not allowed!", HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/confirmPaypal")
	public ResponseEntity<String> successPay(@RequestBody HashMap<String, String> mapper, Principal principal) {
		String paymentId = mapper.get("paymentId");
		String PayerID = mapper.get("PayerID");
		
		try {
			Payment payment = paypalService.executePayment(paymentId, PayerID);
			if (payment.getState().equals("approved")) {

				return new ResponseEntity<String>("Paid succesfully!", HttpStatus.OK);
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}

		return new ResponseEntity<String>("Payment fail!", HttpStatus.BAD_REQUEST);
	}
}
