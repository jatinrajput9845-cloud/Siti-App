package in.sp.main.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.sp.main.dto.CreateEmployeeRequestDTO;
import in.sp.main.entity.Employee;
import in.sp.main.entity.User;
import in.sp.main.repository.EmployeeRepository;
import in.sp.main.repository.UserRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	public EmployeeService(

			EmployeeRepository employeeRepository,

			UserRepository userRepository,

			PasswordEncoder passwordEncoder

	) {

		this.employeeRepository = employeeRepository;

		this.userRepository = userRepository;

		this.passwordEncoder = passwordEncoder;
	}

	// CREATE EMPLOYEE COMPLETE FLOW
	public Employee createEmployee(CreateEmployeeRequestDTO dto) {

		// CHECK USERNAME
		if (userRepository.findByUsername(dto.getUsername()).isPresent()) {

			throw new RuntimeException("Username already exists");
		}

		// CREATE USER
		User user = new User();

		user.setUsername(dto.getUsername());

		user.setPassword(passwordEncoder.encode(dto.getPassword()));

		user.setRole("EMPLOYEE");

		user.setStatus("ACTIVE");

		User savedUser = userRepository.save(user);

		// CREATE EMPLOYEE
		Employee employee = new Employee();

		employee.setName(dto.getName());

		employee.setShopName(dto.getShopName());

		employee.setUser(savedUser);

		return employeeRepository.save(employee);
	}

	// GET ALL EMPLOYEES
	public List<Employee> getAllEmployees() {

		return employeeRepository.findAll();
	}

	// GET EMPLOYEE BY ID
	public Employee getEmployeeById(Long id) {

		return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
	}

	// UPDATE EMPLOYEE
	public Employee updateEmployee(Long id, Employee employee) {

		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Employee not found"));

		existingEmployee.setName(employee.getName());

		existingEmployee.setShopName(employee.getShopName());

		return employeeRepository.save(existingEmployee);
	}

	// DELETE EMPLOYEE
	public void deleteEmployee(Long id) {

		employeeRepository.deleteById(id);
	}
}