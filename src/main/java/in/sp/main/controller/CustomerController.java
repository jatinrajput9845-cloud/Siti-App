package in.sp.main.controller;

import in.sp.main.dto.CreateCustomerRequestDTO;
import in.sp.main.entity.Customer;
import in.sp.main.service.CustomerService;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {

		this.customerService = customerService;
	}

	// SAVE CUSTOMER
	@PostMapping("/create")
	public Map<String, Object> createCustomer(@RequestBody CreateCustomerRequestDTO dto) {

		Customer customer = customerService.createCustomer(dto);

		Map<String, Object> response = new HashMap<>();

		response.put("id", customer.getId());
		response.put("name", customer.getName());
		response.put("username", customer.getUser().getUsername());
		response.put("role", customer.getUser().getRole());
		response.put("email", customer.getEmail());
		response.put("address", customer.getAddress());
		response.put("status", customer.getStatus());

		return response;
	}

	// GET ALL CUSTOMERS
	@GetMapping("/all")
	public List<Customer> getAllCustomer() {

		return customerService.getAllCustomer();
	}

	// GET CUSTOMER BY ID
	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable Long id) {

		return customerService.getCustomerById(id);
	}

	// UPDATE CUSTOMER
	@PutMapping("/update/{id}")
	public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {

		return customerService.updateCustomer(id, customer);
	}

	// DELETE CUSTOMER
	@DeleteMapping("/delete/{id}")
	public String deleteCustomer(@PathVariable Long id) {

		customerService.deleteCustomer(id);

		return "Customer deleted successfully";
	}
	@GetMapping("/names")
	public List<String> getAllCustomerNames() {
		return customerService.getAllCustomerNames();
}
}