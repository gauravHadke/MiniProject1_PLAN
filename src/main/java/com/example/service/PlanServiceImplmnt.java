package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Plan;
import com.example.entity.PlanCategory;
import com.example.repo.PlanCategoryRepo;
import com.example.repo.PlanRepo;

@Service
public class PlanServiceImplmnt implements PlanService{
	
	@Autowired
	private PlanRepo planRepo;
	
	@Autowired
    private PlanCategoryRepo planCategoryRepo;

	@Override
	public Map<Integer, String> getPlanCategory() {
		// TODO Auto-generated method stub
		
		List<PlanCategory> categories = planCategoryRepo.findAll();
		
		Map<Integer,String> categoryMap = new HashMap<>();
		
		categories.forEach(category -> {
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		});
		
		return categoryMap;
	}

	@Override
	public boolean saveplan(Plan plan) {
		// TODO Auto-generated method stub
		Plan save = planRepo.save(plan);
		
//		if(save.getPlanId() != null) {
//			return true;
//		}
//		else{
//			return false;
//		}
		
	//	return save.getPlanId()!=null?true:false;	
		
		return save.getPlanId()!=null;
	}

	@Override
	public List<Plan> getAllPlans() {
		// TODO Auto-generated method stub
		
		return planRepo.findAll();
	}

	@Override
	public Plan getPlanById(Integer planId) {
		// TODO Auto-generated method stub
		System.out.println("Get plan id from req : "+planId);
		Optional<Plan> findById = planRepo.findById(planId);
		if(findById.isPresent()) {
			System.out.println("@@@"+findById.get());
			return findById.get();
		}else {
			System.out.println("Didnt get value");
		}
		return null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
		// TODO Auto-generated method stub
		Plan save = planRepo.save(plan);
		return save.getPlanId()!=null;
		//return false;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		// TODO Auto-generated method 
		boolean status = false;
		try {
			planRepo.deleteById(planId);
			status = true;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		// TODO Auto-generated method stub
		//optional does not allow you to directly operate on object.
	
		Optional<Plan> findById = planRepo.findById(planId);
		if(findById.isPresent()) {
			Plan plan = findById.get();
			plan.setPlanId(planId);
			plan.setActiveSw(status);
			planRepo.save(plan);
			return true;
		}
		return false;
	}

	
}
