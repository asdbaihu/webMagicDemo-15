package com.inspur.get山东省商务厅;

import java.util.List;
import java.util.Date;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import com.inspur.util.MD5;

public class getSdbussinessGovNews implements PageProcessor {
	// 搜索关键词
	private static String keyword = "1";
	// 站点入口定义//山东省商务厅 开发区管理处 通知公告
	private static String inUrl = "http://www.shandongbusiness.gov.cn/Office-10-144.html";
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
		if (page.getUrl().regex("http:\\/\\/www\\.shandongbusiness\\.gov\\.cn\\/Office-10-([\\d]*)\\.html").match()) {
			String sRegular = "//ul[@class='ul list']/li/a/@href";
			System.out.println("AAA : " + page.getUrl());
			// 获取列表页面的链接列表
			List<String> listHref = page.getHtml().xpath(sRegular).all();
			System.out.println("AAA : " + listHref.toString());
			
			// 加入爬虫队列
			page.addTargetRequests(listHref);
		}
		// 2. 如果是用户详细页面
		else {
			num++;
			System.out.println("BBB : "+page.getUrl());
			// 获取详情页面信息
			String title = page.getHtml().xpath("//div[@class='view_mian']/h2/text()").get();
			String cTime = page.getHtml().xpath("//div[@class='view_mian']/h3/span/text()").all().toString(); 
			String subSite = page.getHtml().xpath("//div[@class='view_mian']/h3/span/a/text()").all().toString(); 
			String sInfo = page.getHtml().xpath("//div[@class='view_mian']/div/html()").all().get(0);
			
			System.out.println("  pageKey :"+ MD5.MD5Encode(page.getUrl().toString()) );
			System.out.println("  title :"+ title );
			System.out.println("  subSite :"+ subSite );
			System.out.println("  cTime :"+ cTime );
			System.out.println("  sInfo :"+ sInfo);
		}
	}

	@Override
	public Site getSite() {
		return this.site;
	}

	public static void main(String[] args) {
		long startTime, endTime;
		System.out.println("========小爬虫【启动】喽！=========");
		startTime = new Date().getTime();
		Spider.create(new getSdbussinessGovNews()).addUrl(inUrl).thread(5).run();
		endTime = new Date().getTime();
		System.out.println("========小爬虫【结束】喽！=========");
		System.out.println("一共爬到" + num + "个信息！用时为：" + (endTime - startTime) / 1000 + "s");
	}
}
