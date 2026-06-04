package in.sp.main.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.sp.main.dto.CreateCustomerRequestDTO;
import in.sp.main.entity.Customer;
import in.sp.main.entity.Employee;
import in.sp.main.entity.User;
import in.sp.main.repository.CustomerRepository;
import in.sp.main.repository.CustomerServiceRepository;
import in.sp.main.repository.EmployeeRepository;
import in.sp.main.repository.UserRepository;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	private final UserRepository userRepository;

	private final CustomUserDetailsService customUserDetailsService;

	private final CustomerServiceRepository customerServiceRepository;

	private final PasswordEncoder passwordEncoder;

	private final EmployeeRepository employeeRepository;

	private final EmailService emailService;

	public CustomerService(

			CustomerRepository customerRepository,

			UserRepository userRepository,

			CustomUserDetailsService customUserDetailsService,

			CustomerServiceRepository customerServiceRepository,

			PasswordEncoder passwordEncoder,

			EmployeeRepository employeeRepository,

			EmailService emailService

	) {

		this.customerRepository = customerRepository;

		this.userRepository = userRepository;

		this.customUserDetailsService = customUserDetailsService;

		this.customerServiceRepository = customerServiceRepository;

		this.passwordEncoder = passwordEncoder;

		this.employeeRepository = employeeRepository;

		this.emailService = emailService;
	}

	@Transactional
	// CREATE CUSTOMER COMPLETE FLOW
	public Customer createCustomer(CreateCustomerRequestDTO dto) {

		validateCustomer(dto);

		// CHECK USERNAME
		if (userRepository.findByUsername(dto.getUsername()).isPresent()) {

			throw new RuntimeException("Username already exists");
		}

		// GET LOGGED-IN USER
		User loggedInUser = customUserDetailsService.getCurrentLoggedInUser();

		// FETCH EMPLOYEE
		Employee employee = employeeRepository.findByUserId(loggedInUser.getId())
				.orElseThrow(() -> new RuntimeException("Employee not found"));

		// CREATE USER
		User user = new User();

		user.setUsername(dto.getUsername());

		user.setPassword(passwordEncoder.encode(dto.getPassword()));

		user.setRole("CUSTOMER");

		user.setStatus("ACTIVE");

		User savedUser = userRepository.save(user);

		// CREATE CUSTOMER
		Customer customer = new Customer();

		customer.setName(dto.getName());

		customer.setMobile(dto.getMobile());

		customer.setEmail(dto.getEmail());

		customer.setAddress(dto.getAddress());

		customer.setStatus("ACTIVE");

		customer.setUser(savedUser);

		customer.setCreatedByEmployee(employee);

		Customer savedCustomer = customerRepository.save(customer);

		// CREATE CUSTOMER SERVICE
		
		in.sp.main.entity.CustomerService service = new in.sp.main.entity.CustomerService();

		service.setCustomer(savedCustomer);

		service.setStbNumber(dto.getStbNumber());

		service.setVcNumber(dto.getVcNumber());

		service.setPlanName(dto.getPlanName());

		service.setPlanPrice(dto.getPlanPrice());

		service.setValidityDays(dto.getValidityDays());

		service.setStartDate(LocalDate.now());

		service.setEndDate(LocalDate.now().plusDays(dto.getValidityDays()));

		service.setStatus("ACTIVE");

		customerServiceRepository.save(service);
		String bodyString = "Thanks for Choosing SitiNetworks, Please find username:%s, Your default password is:%s";

		emailService.sendEmailNotification(customer.getEmail(),
				String.format(bodyString, user.getUsername(), dto.getPassword()), "Activation Successfull");

		return savedCustomer;
	}

	// GET ALL CUSTOMERS
	public List<Customer> getAllCustomer() {

		return customerRepository.findAll();
	}

	// GET CUSTOMER BY ID
	public Customer getCustomerById(Long id) {

		return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
	}

	// DELETE CUSTOMER
	public void deleteCustomer(Long id) {

		customerRepository.deleteById(id);
	}

	// UPDATE CUSTOMER
	public Customer updateCustomer(Long id, Customer customer) {

		Customer existingCustomer = customerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer not found"));

		existingCustomer.setName(customer.getName());

		existingCustomer.setMobile(customer.getMobile());

		existingCustomer.setAddress(customer.getAddress());

		existingCustomer.setEmail(customer.getEmail());

		existingCustomer.setStatus(customer.getStatus());

		return customerRepository.save(existingCustomer);
	}

	private void validateCustomer(CreateCustomerRequestDTO dto) {
		if (dto.getUsername().length() < 5) {
			throw new RuntimeException("Username can't be less than 5 letters");
		}
		if (dto.getUsername().length() > 10) {
			throw new RuntimeException("Username can't be more than 10 letters");
		}

	}
}