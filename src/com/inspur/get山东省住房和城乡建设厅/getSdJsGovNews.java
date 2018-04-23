package com.inspur.get山东省住房和城乡建设厅;

import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;
import com.inspur.util.HttpClientUtil;
import com.inspur.util.zpUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import com.inspur.util.MD5;

public class getSdJsGovNews implements PageProcessor {
	// 搜索关键词
	private static String keyword = "1";
	// 站点入口定义//山东省住房和城乡建设厅 通知公告
	// "http://www.sdjs.gov.cn/col/col5/index.html";

	// 通知公告 ("columnid", "5") ("unitid", "127");&endrecord=45
	// private static String inUrl ="http://www.sdjs.gov.cn/module/jslib/jquery/jpage/dataproxy.jsp?startrecord=1&endrecord=45&perpage=15";
	// 人大法律("columnid", "16") ("unitid", "139");&endrecord=14
	// private static String inUrl ="http://www.sdjs.gov.cn/module/jslib/jquery/jpage/dataproxy.jsp?startrecord=1&endrecord=14&perpage=15";
	// 行政法规("columnid", "17") ("unitid", "139");&endrecord=29
	private static String inUrl = "http://www.sdjs.gov.cn/module/jslib/jquery/jpage/dataproxy.jsp?startrecord=1&endrecord=29&perpage=15";
	// 地方性法规("columnid", "18") ("unitid", "139");&endrecord=16	
	// 部门规章("columnid", "19") ("unitid", "139");&endrecord=45
	// 政府规章("columnid", "20") ("unitid", "139");&endrecord=15	
	// 规范性文件("columnid", "21") ("unitid", "139");&endrecord=45	
	// 政策解读("columnid", "34") ("unitid", "139");&endrecord=16

	// 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(10).setSleepTime(1000);
	// 用户数量
	private static int num = 0;

	/**
	 * process 方法是webmagic爬虫的核心<br>
	 * 编写抽取【待爬取目标链接】的逻辑代码在html中。
	 */
	@Override
	public void process(Page page) {

		// 1. 如果是用户列表页面 【入口页面】，将所有用户的详细页面的url放入target集合中。
		if (page.getUrl().regex("http:\\/\\/www\\.sdjs\\.gov\\.cn\\/module\\/jslib\\/jquery\\/jpage\\/dataproxy\\.jsp").match()) {
			List<String> listHref = getPageUrlsPost(inUrl);
			System.out.println("listHref  :  " + listHref);
			// 加入爬虫队列
			page.addTargetRequests(listHref);
		}
		// 2. 如果是用户详细页面
		else {
			num++;
			System.out.println("BBB : " + page.getUrl());
			// 获取详情页面信息
			String title = page.getHtml().xpath("//table[@id='article']/tbody/tr/td/text()").all().get(0);
			String cTime = page.getHtml().xpath("//table[@id='article']/tbody/tr/td/table/tbody/tr/td/html()").all().get(0);
			String sInfo = page.getHtml().xpath("//div[@id='zoom']/html()").get();

			System.out.println("  pageKey :" + MD5.MD5Encode(page.getUrl().toString()));
			System.out.println("  title :" + title);
			System.out.println("  cTime :" + cTime);
			System.out.println("  sInfo :" + sInfo);
		}
	}

	private List<String> getPageUrlsPost(String url) {
		// url =
		// "http://www.sdjs.gov.cn/module/jslib/jquery/jpage/dataproxy.jsp?startrecord=1&endrecord=45&perpage=15";
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("Content-Type", "text/xml;charset=UTF-8");
		paraMap.put("Referer", "http://www.sdjs.gov.cn/col/col5/index.html");
		// paraMap.put("Accept", "application/xml, text/xml, */*; q=0.01");
		// paraMap.put("Accept-Encoding", "gzip, deflate");
		// paraMap.put("Accept-Language:", "zh-CN,zh;q=0.8");
		// paraMap.put("Content-Type",
		// "application/x-www-form-urlencoded; charset=UTF-8");
		// paraMap.put("User-Agent:",
		// "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
		// paraMap.put("X-Requested-With", "XMLHttpRequest");

		Map<String, String> bodyMap = new HashMap<String, String>();
		bodyMap.put("col", "1");
		bodyMap.put("appid", "1");
		bodyMap.put("webid", "1");
		bodyMap.put("path", "/");
		// 通知公告
		// bodyMap.put("columnid", "5");
		// bodyMap.put("unitid", "127");
		// 政策法规 - 行政法规
		bodyMap.put("columnid", "17");
		bodyMap.put("unitid", "139");
		bodyMap.put("sourceContentType", "1");
		// bodyMap.put("webname", "山东省住房和城乡建设厅");
		// bodyMap.put("permissiontype", "0");

		String ret = HttpClientUtil.httpPost(url, paraMap, bodyMap, "UTF-8");
		// System.out.println(ret);
		return zpUtil.getUrlListFromScript(ret, "", "href=\\'([\\s\\S]*?)\\' class=\\'bt_link\\'");
	}

	@Override
	public Site getSite() {
		return this.site;
	}

	public static void main(String[] args) {
		long startTime, endTime;
		System.out.println("========小爬虫【启动】喽！=========");
		startTime = new Date().getTime();
		Spider.create(new getSdJsGovNews()).addUrl(inUrl).thread(5).run();
		endTime = new Date().getTime();
		System.out.println("========小爬虫【结束】喽！=========");
		System.out.println("一共爬到" + num + "个信息！用时为：" + (endTime - startTime) / 1000 + "s");
	}
}
