package compare;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.anthavio.phanbedder.Phanbedder;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ContentHelper {

	/**
 * 获取百度搜索到的链接
 * */
	public static List<String> getSiteByBaidu(String key) throws IOException,
			ParserException {
		String url = String.format("http://www.baidu.com/s?wd=%s", key);
		By queryName = null;
		String resContent = getContentByPhantomjs(url, key, queryName);
		//对网页返回内容进行过滤，查找链接地址的节点
		HasAttributeFilter addressFilter = new HasAttributeFilter("class",
				"c-showurl");
		List<String> list = parseContent(resContent, addressFilter);

		return list;
	}

	/**
 * 获取谷歌搜索到的链接
 * */
	public static List<String> getSiteByGoogle(String key) throws IOException,
			ParserException {
		String url = "https://www.google.com";
		By queryName = By.name("q");
		String resContent = getContentByPhantomjs(url, key, queryName);
		//对网页返回内容进行过滤，查找链接地址的节点
		HasAttributeFilter addressFilter = new HasAttributeFilter("class", "kv");
		List<String> list = parseContent(resContent, addressFilter);

		return list;
	}

	/**
 * 对网页返回内容根据过滤器进行过滤
 * */
	public static List<String> parseContent(String content,
			NodeFilter nodeFilter) throws ParserException {
		Parser resParser = new Parser(content);
		NodeList ndList = resParser.extractAllNodesThatMatch(nodeFilter);
		List<String> urlList = new ArrayList<String>();
		for (int i = 0; i < ndList.size(); i++) {
			Node subNode = ndList.elementAt(i);
			String site = subNode.toPlainTextString();
			site = getSiteName(site);
			urlList.add(site);
		}
		return urlList;
	}

	public static String getContentByPhantomjs(String url, String key, By by)
			throws IOException {
		PhantomJSDriver driver = getDriver();
		driver.get(url);
		if (by != null) {
			WebElement query = driver.findElement(by);
			query.sendKeys(key);
			query.submit();
		}
		String rsString = driver.getPageSource();
		driver.quit();
		return rsString;
	}

	private static PhantomJSDriver getDriver() {
		File phantomjs = Phanbedder.unpack();
		DesiredCapabilities dcaps = new DesiredCapabilities();
		dcaps.setCapability(
				PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				phantomjs.getAbsolutePath());
		PhantomJSDriver driver = new PhantomJSDriver(dcaps);
		return driver;
	}

	private static String getSiteName(String url) {
		String site = "";
		int idxSt = url.indexOf("//");
		int idxEd = url.indexOf("/", idxSt + 2);

		idxSt = idxSt == -1 ? 0 : idxSt + 2;
		site = idxEd == -1 ? url.substring(idxSt) : url.substring(idxSt, idxEd);

		return site;

	}
}