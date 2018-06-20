package com.example.bibingwei.bean;

import java.util.List;

/**
 * @author bibingwei
 * http://news-at.zhihu.com/api/4/news/latest
 */
public class ZhiHu {


    /**
     * date : 20180603
     * stories : [{"title":"虾籽鲜、虾仁弹、虾黄香，这份鲜香三虾面，一年只卖一季","ga_prefix":"060310","images":["https://pic1.zhimg.com/v2-49ffff254ef991db20e9569c03d8cc38.jpg"],"multipic":true,"type":0,"id":9684593},{"images":["https://pic1.zhimg.com/v2-95c0d4599d021d4032af38a404d31278.jpg"],"type":0,"id":9685149,"ga_prefix":"060309","title":"苹果又要开新品发布会，我来大胆预测一下\u2026\u2026"},{"images":["https://pic3.zhimg.com/v2-b1f64ebdfa336fa35a8d9b9d3d5acdf2.jpg"],"type":0,"id":9685215,"ga_prefix":"060309","title":"本周热门精选 · 世界真奇妙"},{"images":["https://pic3.zhimg.com/v2-085bf1a4b2ab2cba10356032664aa07e.jpg"],"type":0,"id":9684422,"ga_prefix":"060308","title":"生物学中有哪些好玩的知识？"},{"images":["https://pic3.zhimg.com/v2-2e0c6cc363a964eb1d4c1f77efbfbb5a.jpg"],"type":0,"id":9683645,"ga_prefix":"060307","title":"好端端的，可有些人就这么活成了喷子"},{"title":"稀有视角、工业精神，细节能力、科学情怀：为什么人们沉迷于《异星工厂》？","ga_prefix":"060307","images":["https://pic1.zhimg.com/v2-a1a25dcabb7c3df1b05798812c94cfe4.jpg"],"multipic":true,"type":0,"id":9685127},{"images":["https://pic4.zhimg.com/v2-4c3338b1e71df6300ce57a3d8cdc0173.jpg"],"type":0,"id":9685206,"ga_prefix":"060307","title":"有哪些典型的「学生思维」？"},{"images":["https://pic1.zhimg.com/v2-89b5a74ba3c50d87b038d1c996225234.jpg"],"type":0,"id":9685145,"ga_prefix":"060306","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic3.zhimg.com/v2-452df7a06cd2030250eb64f9b17a979e.jpg","type":0,"id":9685215,"ga_prefix":"060309","title":"本周热门精选 · 世界真奇妙"},{"image":"https://pic3.zhimg.com/v2-988badd07226804f765ab69293c8b166.jpg","type":0,"id":9684593,"ga_prefix":"060310","title":"虾籽鲜、虾仁弹、虾黄香，这份鲜香三虾面，一年只卖一季"},{"image":"https://pic3.zhimg.com/v2-6eccf37e894a5a876e6ed7d67cd0897a.jpg","type":0,"id":9685149,"ga_prefix":"060309","title":"苹果又要开新品发布会，我来大胆预测一下\u2026\u2026"},{"image":"https://pic4.zhimg.com/v2-59b02840fb9d090a887337b7ba793c43.jpg","type":0,"id":9683645,"ga_prefix":"060307","title":"好端端的，可有些人就这么活成了喷子"},{"image":"https://pic1.zhimg.com/v2-35d4057230b9d51b51576ba1b885c56c.jpg","type":0,"id":9684422,"ga_prefix":"060308","title":"生物学中有哪些好玩的知识？"}]
     */

    private String date;
    private List<StoriesBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public static class StoriesBean {
        /**
         * title : 虾籽鲜、虾仁弹、虾黄香，这份鲜香三虾面，一年只卖一季
         * ga_prefix : 060310
         * images : ["https://pic1.zhimg.com/v2-49ffff254ef991db20e9569c03d8cc38.jpg"]
         * multipic : true
         * type : 0
         * id : 9684593
         */

        private String title;
        private String ga_prefix;
        private boolean multipic;
        private int type;
        private int id;
        private List<String> images;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
