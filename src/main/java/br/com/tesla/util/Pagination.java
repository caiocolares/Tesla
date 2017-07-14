package br.com.tesla.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;

public class Pagination {
	
	public static Map<String,Integer> pagination(Page<?> page){
		int currentPage = page.getNumber();
		int totalPages = page.getTotalPages();
		int numberOfElements = page.getNumberOfElements();
		int size = page.getSize();
		int betweenStart = (currentPage * size) + 1;
		int betweenEnd = numberOfElements + (currentPage * size);
		int firstPage = 0;
		int lastPage = 4;
		int diff = currentPage - 2;
		
		if(diff > 0 && ((lastPage + 1) != totalPages)){
			firstPage = firstPage + diff;
			lastPage = lastPage + diff;
		}

		if(lastPage > (totalPages-1)){
			lastPage = totalPages - 1;
			firstPage = lastPage - 4;
			firstPage = firstPage < 0 ? 0 : firstPage;
		}
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("firstPage",firstPage);
		map.put("lastPage",lastPage);
		map.put("betweenStart",betweenStart);
		map.put("betweenEnd",betweenEnd);
		map.put("total",(int) page.getTotalElements());
		return map;
	}
}
