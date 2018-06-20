package com.example.bibingwei.bean;

import java.util.List;

/**
 * @author bibingwei
 * http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1
 */
public class LuckImage {

    /**
     * error : false
     * results : [{"_id":"5b15ec20421aa97e45f15aae","createdAt":"2018-06-05T09:49:20.355Z","desc":"2018-06-05","publishedAt":"2018-06-05T00:00:00.0Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1fs02a9b0nvj30sg10vk4z.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b14aaa9421aa93df569c6f1","createdAt":"2018-06-04T10:57:45.583Z","desc":"2018-06-04","publishedAt":"2018-06-04T00:00:00.0Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1fryyn63fm1j30sg0yagt2.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b1026ba421aa9029895ba44","createdAt":"2018-06-01T00:45:46.820Z","desc":"2018-06-02","publishedAt":"2018-06-01T00:00:00.0Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1frv03m8ky5j30iz0rltfp.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b0d6946421aa97f0308836b","createdAt":"2018-05-29T22:52:54.29Z","desc":"2018-05-31","publishedAt":"2018-05-31T00:00:00.0Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1frsllc19gfj30k80tfah5.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b0d6895421aa97f0308836a","createdAt":"2018-05-29T22:49:57.62Z","desc":"2018-05-30","publishedAt":"2018-05-30T13:22:16.505Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1frslibvijrj30k80q678q.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b0c2bc3421aa97f0624f447","createdAt":"2018-05-29T00:18:11.714Z","desc":"2018-05-29","publishedAt":"2018-05-29T15:38:50.405Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1frrifts8l5j30j60ojq6u.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b0b5839421aa97f00f67c5c","createdAt":"2018-05-28T09:15:37.475Z","desc":"2018-05-28","publishedAt":"2018-05-28T18:51:58.793Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1frqscr5o00j30k80qzafc.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b06dc9c421aa97f03088341","createdAt":"2018-05-24T23:39:08.401Z","desc":"2018.5.25-1","publishedAt":"2018-05-25T10:30:37.805Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1frmuto5qlzj30ia0notd8.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b02e1cd421aa96031463fe4","createdAt":"2018-05-21T23:12:13.646Z","desc":"2018.5.25","publishedAt":"2018-05-24T11:03:54.588Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1frjd77dt8zj30k80q2aga.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b02e163421aa9602d6abd36","createdAt":"2018-05-21T23:10:27.865Z","desc":"2018.5.23","publishedAt":"2018-05-23T00:22:29.342Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1frjd4var2bj30k80q0dlf.jpg","used":true,"who":"lijinshanmx"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5b15ec20421aa97e45f15aae
         * createdAt : 2018-06-05T09:49:20.355Z
         * desc : 2018-06-05
         * publishedAt : 2018-06-05T00:00:00.0Z
         * source : web
         * type : 福利
         * url : http://ww1.sinaimg.cn/large/0065oQSqly1fs02a9b0nvj30sg10vk4z.jpg
         * used : true
         * who : lijinshanmx
         */

        private String _id;
        private String url;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
