package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TxtHelper {
	
	//private static Map<String,List<String>> dataMap = new HashMap<String,List<String>>();
	private static BufferedReader fi;
	private static String fileName = "C:\\1.txt";
	
	public static Map<String,List<String>> initData() throws IOException{
		/*
		 * 数据文件中测试案例和测试数据在同一行
		 */	
		Map<String,List<String>> dataMap = new HashMap<String,List<String>>();
		dataMap.clear();
		String rec = null;
		int row = 0;
		fi = new BufferedReader(
				new InputStreamReader(
					new FileInputStream(fileName),"utf-8"));
		
		while((rec = fi.readLine()) != null){
			if(row==0){
				rec = rec.substring(1);
			}
			row++;
			if(rec.trim().isEmpty()||rec.length()==0||rec == null)
				continue;			
			
			//判断文件格式
			int count =0;
			for(int i=0;i<rec.length();i++){
				if(rec.toCharArray()[i] == '|'){
					count++;
				}
			}
			switch(count){
			case 0: 
				rec = rec + "| |";
				break;
			case 1:
				rec = rec +" |";
				break;
			case 2:
				break;
			default:
				System.err.println("---------File Error：Please check!--------------");
			
			}

			String[] caseDataArr = null;
			caseDataArr = rec.split("\\|");
			
			ArrayList<String> list = new ArrayList<String>();
			String[] tempStr;
			caseDataArr[1] = caseDataArr[1] + " ,";
			tempStr = caseDataArr[1].split(",");
			for(int j=0;j<tempStr.length;j++){	
				tempStr[j] = tempStr[j].trim();
				if(tempStr[j].isEmpty()){
					tempStr[j] = null;
					}
				}
			Collections.addAll(list,tempStr);
			dataMap.put(caseDataArr[0], list);
		}	
	fi.close();
	return dataMap;
	}
	
	public static String getData(String caseName,int index) throws IOException{
		Map<String,List<String>> dataMap = new HashMap<String,List<String>>();
		dataMap = initData();
		ArrayList<String> list = new ArrayList<String>();
		list = (ArrayList<String>) dataMap.get(caseName);
		if(list == null ||list.size()<1){
			return "";
		}
		if(index<0 || index>list.size()){
			System.err.println("-------------Data ("+caseName+") index is out of range-------");
			return "";
		}
		System.out.println(dataMap);
		return  list.get(index);
	}
}
