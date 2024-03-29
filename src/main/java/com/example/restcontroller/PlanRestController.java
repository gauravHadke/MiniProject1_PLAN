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

import com.example.Application;
import com.example.appProperties.ApplicationProperties;
import com.example.constants.AppConstants;
import com.example.entity.Plan;
import com.example.service.PlanService;

import ch.qos.logback.core.model.processor.AppenderModelHandler;

@RestController
public class PlanRestController {
	
	private PlanService planService;
	
	private ApplicationProperties applicationProperties;
	
	Map<String,String> message;
	
	public PlanRestController(PlanService planService , ApplicationProperties applicationProperties) {
		// TODO Auto-generated constructor stub
		this.planService =  planService;
		this.applicationProperties = applicationProperties;
		this.message = applicationProperties.getMessage();
	}
	
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
		//System.out.println("**** Plan Name => "+plan.getPlanName());
	//	Map<String,String> message = applicationProperties.getMessage();
		System.out.println("Check map "+message.get("planSaveSucc"));
		
		boolean isSavePlan = planService.saveplan(plan);
		String msg = AppConstants.EMPTY_STRINGS;
		
		if(isSavePlan) {
			msg = message.get(AppConstants.PLAN_SAVED_SUCCESS);
		}else {
			msg = message.get(AppConstants.PLAN_SAVED_FAIL);
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
	//	Map<String,String> message = applicationProperties.getMessage();
		boolean isUpdated = planService.updatePlan(plan);
		String msg =AppConstants.EMPTY_STRINGS;
		if(isUpdated) {
			msg = message.get(AppConstants.PLAN_UPDATED_SUCC);
		}else {
			msg = message.get(AppConstants.PLAN_UPDATED_FAIL);
		}
		
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
		//Map<String,String> message = applicationProperties.getMessage();
		boolean deletePlanById = planService.deletePlanById(planId);
		String msg = AppConstants.EMPTY_STRINGS;
		if(deletePlanById) {
			msg = message.get(AppConstants.PLAN_DELETED_SUCC);
		}else {
			msg = message.get(AppConstants.PLAN_DELETE_FAIL);
		}
		
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId,@PathVariable String status){
		boolean isStatusChange = planService.planStatusChange(planId, status);
	//	Map<String,String> message = applicationProperties.getMessage();
		String msg = AppConstants.EMPTY_STRINGS;
		if(isStatusChange) {
			msg = message.get(AppConstants.PLAN_STATUS_CHANGE_SUCCESS);
		}else {
			msg = message.get(AppConstants.PLAN_STATUS_CHANGE_FAIL);
		}
		
		return new ResponseEntity<>(msg , HttpStatus.OK);
	}
	
}
