package com.example.restcontroller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.appProperties.ApplicationProperties;
import com.example.constants.AppConstants;
import com.example.entity.Plan;
import com.example.service.PlanService;

@RestController
public class PlanRestController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(PlanRestController.class);

	
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
		logger.info("inside method Get the PLan categories in Map ");
		Map<Integer,String> planCategory = planService.getPlanCategory();
		return new ResponseEntity<>(planCategory , HttpStatus.OK);
	}
	
	@PostMapping(value =  "/plan")
	public ResponseEntity<String> saveplan(@RequestBody Plan plan){
		logger.info("Get the Plan Object to save the plan : "+plan.toString());
		System.out.println("Plan obj "+plan);
	//	Map<String,String> message = applicationProperties.getMessage();
	//	System.out.println("Check map "+message.get("planSaveSucc"));
		
		boolean isSavePlan = planService.saveplan(plan);
		String msg = AppConstants.EMPTY_STRINGS;
		
		if(isSavePlan) {
			msg = message.get(AppConstants.PLAN_SAVED_SUCCESS);
			logger.info("PLan saved "+plan);
		}else {
			msg = message.get(AppConstants.PLAN_SAVED_FAIL);
			logger.error("Dosnt save "+plan);
		}
		
		return new ResponseEntity<>(msg,HttpStatus.CREATED);
	}
	
	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> getPlans(){	
		List<Plan> allPlans = planService.getAllPlans();
		logger.info("List of plans : "+allPlans);
		return new ResponseEntity<>(allPlans,HttpStatus.OK);
	}
	
	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId){
		
		Plan planById = planService.getPlanById(planId);
		logger.info("Plan details by plan id : "+planById);
		return new ResponseEntity<>(planById , HttpStatus.OK);
	}
	
	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan){
	//	Map<String,String> message = applicationProperties.getMessage();
		logger.info("Plan to update : "+plan);
		boolean isUpdated = planService.updatePlan(plan);
		String msg =AppConstants.EMPTY_STRINGS;
		if(isUpdated) {
			msg = message.get(AppConstants.PLAN_UPDATED_SUCC);
			logger.info("Plan Updated : "+plan);
		}else {
			msg = message.get(AppConstants.PLAN_UPDATED_FAIL);
			logger.info("Plan doesnt Updated : "+plan);
		}
		
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
		//Map<String,String> message = applicationProperties.getMessage();
		logger.info("Deleting plan by id : "+planId);
		boolean deletePlanById = planService.deletePlanById(planId);
		String msg = AppConstants.EMPTY_STRINGS;
		if(deletePlanById) {
			msg = message.get(AppConstants.PLAN_DELETED_SUCC);
			logger.info("Deleted plan ");
		}else {
			msg = message.get(AppConstants.PLAN_DELETE_FAIL);
			logger.info("PLan doesnt deleted");
		}
		
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId,@PathVariable String status){
		boolean isStatusChange = planService.planStatusChange(planId, status);
		logger.info("status change by id and status :"+planId+" "+status);
	//	Map<String,String> message = applicationProperties.getMessage();
		String msg = AppConstants.EMPTY_STRINGS;
		if(isStatusChange) {  
			msg = message.get(AppConstants.PLAN_STATUS_CHANGE_SUCCESS);
			logger.info("Status changed"+status);
		}else {
			msg = message.get(AppConstants.PLAN_STATUS_CHANGE_FAIL);
			logger.info("Status doent changed");
		}
		
		return new ResponseEntity<>(msg , HttpStatus.OK);
	}
	
}
