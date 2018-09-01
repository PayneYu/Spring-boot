/**
 * 
 */
package com.beike.wechat.pojo.message;

import java.util.List;

/**
 * @author yuhuizhe
 *
 */
public class News {
	//文章列表
   private List<Article> articles;

   public List<Article> getArticles() {
       return articles;
   }

   public void setArticles(List<Article> articles) {
       this.articles = articles;
   }
}
