package compare;

import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class CompareResult {

	public static void main(String[] args) throws Exception {
		CompareResult objCompareResult = new CompareResult();
		objCompareResult.searchByBaidu("¬Ï“œ");

	}
	
	public void searchByBaidu(String key) throws Exception {
		String urlBaidu = String.format("http://www.baidu.com/s?wd=%s", key);
		URLConnection connection = getUrlConnection(urlBaidu, key);
		getSearchedUrlsByBaidu(connection);
	}

	public void searchByGoogle(String key) throws Exception {
		String urlGoogle = String
				.format("https://www.google.com.tw/#q=%s", key);
		getUrlConnection(urlGoogle, key);
	}

	public URLConnection getUrlConnection(String site, String key)
			throws Exception {
		URL url = new URL(site);
		URLConnection rc = url.openConnection();
		return rc;
	}

	public List<String> getSearchedUrlsByBaidu(URLConnection rc)
			throws ParserException {
		Parser resParser = new Parser((HttpURLConnection) rc);
		HasAttributeFilter attribute2Filter = new HasAttributeFilter("class",
				"c-showurl");
		NodeList ndList = resParser.extractAllNodesThatMatch(attribute2Filter);
		List<String> urlList = new ArrayList<String>();
		for (int i = 0; i < ndList.size(); i++) {
			Node subNode = ndList.elementAt(i);
			urlList.add(subNode.toPlainTextString().replaceAll("&nbsp;", ""));

		}
		return urlList;
	}
	
	public BigDecimal getSimailarityOfList(List<String>lstSrc,List<String>lstDes){		
		lstSrc.retainAll(lstDes);
		
		BigDecimal percent;
		BigDecimal sameDecimal=new BigDecimal(lstSrc.size());
		BigDecimal totalDecimal=new BigDecimal(lstDes.size());
			
		percent=sameDecimal.multiply(new BigDecimal(100)).divide(totalDecimal,2,BigDecimal.ROUND_HALF_UP);
		return percent;	
	}
	
	
}