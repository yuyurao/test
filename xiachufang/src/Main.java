import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {

    public static List<String[]> foodSpider() throws IOException {
        String url = "http://www.xiachufang.com/explore/";
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";

        Document doc = Jsoup.connect(url)
                .userAgent(userAgent)
                .get();

        Elements foods = doc.select("div.info.pure-u");
        List<String[]> listAll = new ArrayList<>();

        for (Element food : foods) {
            Element tagA = food.selectFirst("a");
            //String name = tagA.text().substring(17, tagA.text().length() - 3);
            String name = tagA.text();
            //System.out.println(name);
            String urlFood = "http://www.xiachufang.com" + tagA.attr("href");
           //System.out.println(urlFood);
            Element tagP = food.selectFirst("p.ing.ellipsis");
            String ingredients = tagP.text().substring(1, tagP.text().length() - 1);
            //System.out.println(ingredients);
            listAll.add(new String[]{name, urlFood, ingredients});
            //listAll.add(new String[]{ urlFood, ingredients});
        }

        return listAll;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("开始一次任务");
        List<String[]> listAll = foodSpider();
        for (String[] element : listAll) {
            for (String item : element) {
                System.out.println(item);
            }

        }

        System.out.println("任务完成");
    }
}