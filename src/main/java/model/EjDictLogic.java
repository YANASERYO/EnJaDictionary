package model;

import java.util.ArrayList;
import java.util.List;

public class EjDictLogic {

	public List<String> validate(String word, String mean,String maxCount) {
		List<String> errorList = new ArrayList<>();
		
		if (word == null) {
			word = "";
		}
		if (mean == null) {
			mean = "";
		}
		if (maxCount == null || maxCount.length() == 0) {
			errorList.add("最大検索数を指定して下さい。");
		} else {
			try {
				int count = Integer.parseInt(maxCount);

				if (count < 1 || count > 50) {
					errorList.add("最大検索件数は1～50です。");
				}
			} catch (NumberFormatException e) {
				errorList.add("最大検索件数は整数で指定すること。");
			}
		}
		return errorList;
	}
	public List<EjDict> execute(String word,String mean, String maxCount) {
		int count = Integer.parseInt(maxCount);
		if (word == null) {
			word = "";
		}
		if (mean == null) {
			mean = "";
		}
		DictionaryDAO dao = new DictionaryDAO();
		List<EjDict> dictList = dao.findByWord(word, mean,count);
		
		int listSize = dictList.size(); 
		
		for (int i = 0; i < listSize; i++) {
			EjDict dict = dictList.get(i); 
			String oldMean = dict.getExplanation(); 
			String newMean = oldMean.replace("/", "<br>"); 
			
		dict.setExplanation(newMean); 
		}
		return dictList;
	}
}