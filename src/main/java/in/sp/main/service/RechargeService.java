package in.sp.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.dto.RechargeDTO;
import in.sp.main.entity.Customer;
import in.sp.main.entity.CustomerService;
import in.sp.main.entity.Employee;
import in.sp.main.entity.Recharge;
import in.sp.main.repository.CustomerRepository;
import in.sp.main.repository.CustomerServiceRepository;
import in.sp.main.repository.EmployeeRepository;
import in.sp.main.repository.RechargeRepository;

@Service
public class RechargeService {

	@Autowired
	private RechargeRepository rechargeRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerServiceRepository customerServiceRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmailService emailService;

	// SAVE RECHARGE
	public Recharge saveRecharge(RechargeDTO rechargeDTO) {

		// FETCH CUSTOMER
		Customer customer = customerRepository.findById(rechargeDTO.getCustomerId())
				.orElseThrow(() -> new RuntimeException("Customer not found"));

		// FETCH SERVICE
		CustomerService service = customerServiceRepository.findById(rechargeDTO.getServiceId())
				.orElseThrow(() -> new RuntimeException("Service not found"));

		// FETCH EMPLOYEE
		Employee employee = employeeRepository.findById(rechargeDTO.getRechargedByEmployeeId())
				.orElseThrow(() -> new RuntimeException("Employee not found"));

		Recharge recharge = new Recharge();

		recharge.setCustomer(customer);
		
		recharge.setService(service);
		
		recharge.setRechargedByEmployee(employee);

		recharge.setAmount(rechargeDTO.getAmount());

		recharge.setPaymentMode(rechargeDTO.getPaymentMode());

		recharge.setValidityTill(rechargeDTO.getValidityTill());

		// SAVE RECHARGE
		Recharge savedRecharge = rechargeRepository.save(recharge);

		// UPDATE SERVICE VALIDITY
		service.setEndDate(rechargeDTO.getValidityTill());

		customerServiceRepository.save(service);

		// SEND EMAIL
		String bodyString1 = "Recharge successfully done with : %d";
		emailService.sendEmailNotification(customer.getEmail(), String.format(bodyString1, recharge.getAmount()),
				"Recharge Done!");

		return savedRecharge;
	}

	// GET ALL RECHARGES
	public List<Recharge> getAllRecharges() {

		return rechargeRepository.findAll();
	}

	// GET RECHARGE BY ID
	public Recharge getRechargeById(Long id) {

		return rechargeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recharge not found"));
	}

	// DELETE RECHARGE
	public void deleteRecharge(Long id) {

		rechargeRepository.deleteById(id);
	}

}