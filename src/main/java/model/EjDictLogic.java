package model;

import java.util.ArrayList;
import java.util.List;

public class EjDictLogic {

	public List<String> validate(String word, String mean,String maxCount) {
		List<String> errorList = new ArrayList<>();

		if (word == null || word.length() == 0) {
			errorList.add("検索する英単語を指定して下さい。");
		}

		if (maxCount == null || maxCount.length() == 0) {
			errorList.add("最大検索件数を指定して下さい。");
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

		DictionaryDAO dao = new DictionaryDAO();
		List<EjDict> dictList = dao.findByWord(word, count);

		return dictList;
	}
}