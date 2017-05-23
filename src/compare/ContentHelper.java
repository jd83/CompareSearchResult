package compare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ContentHelper{
	public static void main(String[] args) throws IOException {
		getContentByPhantomjs("htt2p://www.baidu.com","");
	}
	public static String getContentByPhantomjs(String site, String key) throws IOException {		
		Runtime rt= Runtime.getRuntime();
		Process p = rt.exec("/CompareSearchResult/lib/phantomjs.exe /CompareSearchResult/resource/code.js "+site);
		InputStream inputStream = p.getInputStream();     
        BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream));     
        StringBuffer sBuffer = new StringBuffer();     
        String tmp = "";     
        while((tmp = bReader.readLine())!=null){     
            sBuffer.append(tmp);     
        }        
        System.out.println(sBuffer.toString());
        return sBuffer.toString();     
	}
}