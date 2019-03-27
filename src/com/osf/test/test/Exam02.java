package com.osf.test.test;
//beanutils
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.osf.test.vo.Food;

public class Exam02 {
	public static void main(String[] args) {
		Map<String,String> foodMap = new HashMap<>();
		foodMap.put("foodNum","2");
		foodMap.put("foodName","갈비");
		foodMap.put("foodPrice","15000");
	
		Food f = new Food();
		
		try {
			BeanUtils.populate(f, foodMap);
			System.out.println(f);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
