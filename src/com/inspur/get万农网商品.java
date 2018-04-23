package com.inspur;

import java.util.Date;
import com.inspur.util.MD5;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class get万农网商品 implements PageProcessor {
	// 站点入口定义//
	private static String inUrl = "http://www.10000n.cn/gongying/show.php?itemid=12455";
	// 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(10).setSleepTime(1000);
	// 用户数量
	private static int num = 0;
	// 搜索关键词
	private static String keyword = "4";

	/**
	 * process 方法是webmagic爬虫的核心<br>
	 * 编写抽取【待爬取目标链接】的逻辑代码在html中。
	 */
	@Override
	public void process(Page page) {
			num++;
			System.out.println("BBB : "+page.getUrl());
			// 获取详情页面信息
			String title = page.getHtml().xpath("//h1[@id='title']/text()").get();
			String cTime = page.getHtml().xpath("//td[@class='f_b f_orange']/text()").get(); 
			String sInfo = page.getHtml().xpath("//div[@class='tb']/table/tbody/tr/td/text()").all().toString();
			
			System.out.println("  pageKey :"+ MD5.MD5Encode(page.getUrl().toString()) );
			System.out.println("  title :"+ title );
			System.out.println("  cTime :"+ cTime );
			System.out.println("  sInfo :"+ sInfo );
	}

	@Override
	public Site getSite() {
		return this.site;
	}

	public static void main(String[] args) {
		long startTime, endTime;
		System.out.println("========小爬虫【启动】喽！=========");
		startTime = new Date().getTime();
		Spider.create(new get万农网商品()).addUrl(inUrl).thread(5).run();
		endTime = new Date().getTime();
		System.out.println("========小爬虫【结束】喽！=========");
		System.out.println("一共爬到" + num + "个信息！用时为：" + (endTime - startTime) / 1000 + "s");
	}
}
