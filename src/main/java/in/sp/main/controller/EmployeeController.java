package in.sp.main.controller;

import java.util.List;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import in.sp.main.dto.CreateEmployeeRequestDTO;
import in.sp.main.entity.Employee;
import in.sp.main.service.EmployeeService;

@RestController
@RequestMapping("/employees")
@CrossOrigin
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {

		this.employeeService = employeeService;
	}

	// CREATE EMPLOYEE
	@PostMapping("/create")
	public Employee createEmployee(@RequestBody CreateEmployeeRequestDTO dto) {

		return employeeService.createEmployee(dto);
	}

	// GET ALL EMPLOYEES
	@GetMapping("/all")
	public List<Employee> getAllEmployees() {

		return employeeService.getAllEmployees();
	}

	// GET EMPLOYEE BY ID
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable Long id) {

		return employeeService.getEmployeeById(id);
	}

	// UPDATE EMPLOYEE
	@PutMapping("/update/{id}")
	public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {

		return employeeService.updateEmployee(id, employee);
	}

	// DELETE EMPLOYEE
	@DeleteMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable Long id) {

		employeeService.deleteEmployee(id);

		return "Employee deleted successfully";
	}

//    public static void main(String[] args) {
//    	System.out.println(
//    		    new BCryptPasswordEncoder()
//    		        .encode("Admin123")
//    		);
//	}
}