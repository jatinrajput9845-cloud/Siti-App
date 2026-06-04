package in.sp.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import in.sp.main.dto.RechargeDTO;
import in.sp.main.entity.Recharge;
import in.sp.main.service.RechargeService;

@RestController
@RequestMapping("/recharge")
@CrossOrigin
public class RechargeController {

	@Autowired
	private RechargeService rechargeService;

	// SAVE RECHARGE
	@PostMapping("/save")
	public Recharge saveRecharge(@RequestBody RechargeDTO recharge) {

		return rechargeService.saveRecharge(recharge);
	}

	// GET ALL RECHARGES
	@GetMapping("/all")
	public List<Recharge> getAllRecharges() {

		return rechargeService.getAllRecharges();
	}

	// GET RECHARGE BY ID
	@GetMapping("/{id}")
	public Recharge getRechargeById(@PathVariable Long id) {

		return rechargeService.getRechargeById(id);
	}

	// DELETE RECHARGE
	@DeleteMapping("/delete/{id}")
	public String deleteRecharge(@PathVariable Long id) {

		rechargeService.deleteRecharge(id);

		return "Deleted successfully";
	}
}