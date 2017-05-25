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
 * ����Ӣ����������������غ���
 * */
	public void testEnglishSearch() throws ParserException, IOException {
		String key = "test";
		BigDecimal percent = testSiteLike(key);
		System.out.println(String.format("English search: Similarity:%s%%",
				percent.toString()));
	}
	
	/**
 * ����������������������غ���
 * */
	public void testChineseSearch() throws ParserException, IOException {
		String key = "����";
		BigDecimal percent = testSiteLike(key);
		System.out.println(String.format("Chinese search: Similarity:%s%%",
				percent.toString()));
	}
	
	/**
 * ���ذٶȺ͹ȸ�����������ӵ�ַ�����ƶ�
 * */
	public BigDecimal testSiteLike(String key) throws ParserException,
			IOException {
		List<String> baiduList = ContentHelper.getSiteByBaidu(key);
		List<String> googleList = ContentHelper.getSiteByGoogle(key);
		BigDecimal percent = getSimilarityOfList(baiduList, googleList);
		return percent;
	}
	
	/**
 * ��������List�����ƶ�
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