package compare;

import java.io.*;
import java.net.*;

public class CompareResult{
	CompareResult objCompareResult=new CompareResult();
	public static void main(String[] args) throws Exception {
		CompareResult objCompareResult=new CompareResult();
		objCompareResult.search(null, "test");
	}
	
	public void searchByBaidu(String key) throws Exception{
		String urlBaidu=String.format("http://www.baidu.com/s?wd=%s", key);
		objCompareResult.search(urlBaidu,key);
	}
	
	public void searchByGoogle(String key) throws Exception{
		String urlGoogle=String.format("https://www.google.com.tw/#q=%s", key);
		objCompareResult.search(urlGoogle,key);
	}
	
	public String search(String site, String key) throws Exception{
		URL url=new URL(site);
		URLConnection rc=url.openConnection();
		BufferedReader inBufferedReader =new BufferedReader(new InputStreamReader(rc.getInputStream()));
		String inputStr;
		while ((inputStr=inBufferedReader.readLine())!=null) {
			System.out.println(inputStr);
		}
		inBufferedReader.close();
		return inputStr;
	}
}