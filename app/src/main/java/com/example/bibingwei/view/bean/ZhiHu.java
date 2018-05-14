package com.example.bibingwei.view.bean;

import java.util.List;

/**
 * @author bibingwei
 * http://news-at.zhihu.com/api/4/news/latest
 */
public class ZhiHu {

    /**
     * date : 20180514
     * stories : [{"images":["https://pic3.zhimg.com/v2-d0c6d4b8cbd49ea6bd64553b223d8e5e.jpg"],"type":0,"id":9682758,"ga_prefix":"051419","title":"DNF 「3 年封号」有冤案？这群「土豪」想打个官司验证一下"},{"images":["https://pic3.zhimg.com/v2-048b5596c3fa3383475a351c03b406da.jpg"],"type":0,"id":9682611,"ga_prefix":"051419","title":"每天睡觉前，一边恨自己，一边往嘴里塞东西"},{"images":["https://pic2.zhimg.com/v2-6f35c6559fe1a680b73a12f72e120f59.jpg"],"type":0,"id":9681522,"ga_prefix":"051418","title":"说不租就不租，说赶人就赶人，房东有这么大权利吗？"},{"images":["https://pic1.zhimg.com/v2-9445099d057ba6ff93027b77758caa8c.jpg"],"type":0,"id":9682607,"ga_prefix":"051417","title":"两种人更容易手机上瘾：不愿手闲着，害怕被落下"},{"images":["https://pic1.zhimg.com/v2-9b425affd176721457a58461bb68dfb4.jpg"],"type":0,"id":9682546,"ga_prefix":"051416","title":"撕掉「亏损」标签，京东也丢掉了「增长」"},{"title":"《复联 3》的画面看起来那么紫，真不是我灭霸的锅","ga_prefix":"051414","images":["https://pic1.zhimg.com/v2-c2536ea5a449cb8d514c759ede07a20c.jpg"],"multipic":true,"type":0,"id":9682512},{"images":["https://pic2.zhimg.com/v2-00b4f0314470212f6550f367bb724679.jpg"],"type":0,"id":9682515,"ga_prefix":"051412","title":"被「悬赏」的顺风车司机刘振华"},{"images":["https://pic1.zhimg.com/v2-df5b339c830b5b95e74c80fc411a188c.jpg"],"type":0,"id":9682489,"ga_prefix":"051412","title":"大误 · 灭霸老爹的不归之路（有剧透）"},{"images":["https://pic1.zhimg.com/v2-6e94b422f748674e0660e69480bf777c.jpg"],"type":0,"id":9682562,"ga_prefix":"051410","title":"拔牙时感到疼痛，千万别忍着"},{"images":["https://pic1.zhimg.com/v2-87157c8cabae92d36a6eb8c14045fe78.jpg"],"type":0,"id":9682193,"ga_prefix":"051409","title":"万一你以后有了自己的家，装修时千万别和我犯同样的错误"},{"images":["https://pic3.zhimg.com/v2-910312326beb3651e729b8a0f213aaee.jpg"],"type":0,"id":9682012,"ga_prefix":"051408","title":"摩拜被收购后，回头再看它的诞生成长，活脱脱一部启示录"},{"title":"你所爱的表情包们已经老了","ga_prefix":"051407","images":["https://pic3.zhimg.com/v2-c06bf02582b22e10b4fc2936d6055992.jpg"],"multipic":true,"type":0,"id":9682257},{"images":["https://pic3.zhimg.com/v2-9c01eeb776cd5e1ed85186be1504d38a.jpg"],"type":0,"id":9682399,"ga_prefix":"051407","title":"错一次损失几百万，一个容错率几乎为零的职业"},{"images":["https://pic2.zhimg.com/v2-b0e80f207e29d099c11410a939ab37d5.jpg"],"type":0,"id":9682522,"ga_prefix":"051406","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic2.zhimg.com/v2-6ed49ec115e57fa2572cb44a84baa87d.jpg","type":0,"id":9682546,"ga_prefix":"051416","title":"撕掉「亏损」标签，京东也丢掉了「增长」"},{"image":"https://pic2.zhimg.com/v2-767fefc0a100dfdd9a5b9995b1bb8c55.jpg","type":0,"id":9682611,"ga_prefix":"051419","title":"每天睡觉前，一边恨自己，一边往嘴里塞东西"},{"image":"https://pic4.zhimg.com/v2-9ad23516c8714c58b4a522fa0dd37ebf.jpg","type":0,"id":9682607,"ga_prefix":"051417","title":"两种人更容易手机上瘾：不愿手闲着，害怕被落下"},{"image":"https://pic2.zhimg.com/v2-ed5ebb65cbadaab28e4aa253dbdabfe9.jpg","type":0,"id":9682512,"ga_prefix":"051414","title":"《复联 3》的画面看起来那么紫，真不是我灭霸的锅"},{"image":"https://pic2.zhimg.com/v2-6123abf6eb806373d0ff0540d219bff5.jpg","type":0,"id":9682515,"ga_prefix":"051412","title":"被「悬赏」的顺风车司机刘振华"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

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

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic3.zhimg.com/v2-d0c6d4b8cbd49ea6bd64553b223d8e5e.jpg"]
         * type : 0
         * id : 9682758
         * ga_prefix : 051419
         * title : DNF 「3 年封号」有冤案？这群「土豪」想打个官司验证一下
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;

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

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
    }
}
