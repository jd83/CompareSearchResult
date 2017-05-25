package compare;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.htmlparser.util.ParserException;

public class CompareResult {

	public static void main(String[] args) throws Exception {
		CompareResult objCompareResult = new CompareResult();
		objCompareResult.testEnglishSearch();
		objCompareResult.testChineseSearch();

	}
	
	/**
 * 测试英文搜索到搜索结果重合率
 * */
	public void testEnglishSearch() throws ParserException, IOException {
		String key = "test";
		BigDecimal percent = testSiteLike(key);
		System.out.println(String.format("English search: Similarity:%s%%",
				percent.toString()));
	}
	
	/**
 * 测试中文搜索到搜索结果重合率
 * */
	public void testChineseSearch() throws ParserException, IOException {
		String key = "蚂蚁";
		BigDecimal percent = testSiteLike(key);
		System.out.println(String.format("Chinese search: Similarity:%s%%",
				percent.toString()));
	}
	
	/**
 * 返回百度和谷歌搜索结果链接地址的相似度
 * */
	public BigDecimal testSiteLike(String key) throws ParserException,
			IOException {
		List<String> baiduList = ContentHelper.getSiteByBaidu(key);
		List<String> googleList = ContentHelper.getSiteByGoogle(key);
		BigDecimal percent = getSimilarityOfList(baiduList, googleList);
		return percent;
	}
	
	/**
 * 返回两个List的相似度
 * */
	public BigDecimal getSimilarityOfList(List<String> lstSrc,
			List<String> lstDes) {
		lstSrc.retainAll(lstDes);

		BigDecimal percent;
		BigDecimal sameDecimal = new BigDecimal(lstSrc.size());
		BigDecimal totalDecimal = new BigDecimal(lstDes.size());

		percent = sameDecimal.multiply(new BigDecimal(100)).divide(
				totalDecimal, 2, BigDecimal.ROUND_HALF_UP);
		return percent;
	}

}