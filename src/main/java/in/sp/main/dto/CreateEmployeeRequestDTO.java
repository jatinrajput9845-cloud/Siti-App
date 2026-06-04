package in.sp.main.dto;

//import in.sp.main.entity.Employee;
//import in.sp.main.entity.User;

public class CreateEmployeeRequestDTO {

    // EMPLOYEE DETAILS
    private String name;

    private String shopName;

    // LOGIN DETAILS
    private String username;

    private String password;

    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
//    public CreateEmployeeRequestDTO mapToDTO(Employee employee){
//    	this.setName(employee.getName());
//    	this.setPassword(employee.getShopName());
//    	return this;
//    	
//    }
}