package com.stone.suponenovel.core.pageprocessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author qun.zheng
 * @description: TODO
 * @date 2019-08-29 22:20
 */
public class ZongHengPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
//        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("title", page.getHtml().xpath("/html/body//div[@class='book-main fl']//div[@class='tit']/a/text()").toString());
        page.putField("url", page.getHtml().xpath("/html/body//div[@class='book-main fl']//div[@class='tit']/a/@href").toString());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new ZongHengPageProcessor()).addUrl("http://book.zongheng.com/book/672340.html").thread(5).run();
    }
}
