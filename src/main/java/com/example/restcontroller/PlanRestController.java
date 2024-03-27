package com.example.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Plan;
import com.example.service.PlanService;

@RestController
public class PlanRestController {
	
	@Autowired
	private PlanService planService;
	
	@GetMapping(value = "/getDemo")
	public Plan planGet() {
		return new Plan();
	}
	
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer,String>> planCategories(){
		Map<Integer,String> planCategory = planService.getPlanCategory();
		return new ResponseEntity<>(planCategory , HttpStatus.OK);
	}
	
	@PostMapping(value =  "/plan")
	public ResponseEntity<String> saveplan(@RequestBody Plan plan){
		System.out.println("**** Plan Name => "+plan.getPlanName());
		boolean isSavePlan = planService.saveplan(plan);
		String msg = "";
		if(isSavePlan) {
			msg = "Plan Saved";
		}else {
			msg = "Plan not saved";
		}
		
		return new ResponseEntity<>(msg,HttpStatus.CREATED);
	}
	
	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> getPlans(){		
		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans,HttpStatus.OK);
	}
	
	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId){
		
		Plan planById = planService.getPlanById(planId);
		System.out.println("-->"+planById);
		return new ResponseEntity<>(planById , HttpStatus.OK);
	}
	
	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan){
		
		boolean isUpdated = planService.updatePlan(plan);
		String msg = "";
		if(isUpdated) {
			msg = "Plan Updated";
		}else {
			msg = "Plan not updated";
		}
		
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
		
		boolean deletePlanById = planService.deletePlanById(planId);
		String msg = "";
		if(deletePlanById) {
			msg = "Plan deleted";
		}else {
			msg = "Plan not deleted";
		}
		
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId,@PathVariable String status){
		
		boolean isStatusChange = planService.planStatusChange(planId, status);
		String msg = "";
		if(isStatusChange) {
			msg = "Status changed";
		}else {
			msg = "Status not changed";
		}
		
		return new ResponseEntity<>(msg , HttpStatus.OK);
	}
	
}
