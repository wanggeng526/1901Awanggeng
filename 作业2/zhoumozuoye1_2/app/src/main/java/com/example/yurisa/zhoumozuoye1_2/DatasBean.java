package com.example.yurisa.zhoumozuoye1_2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class DatasBean {
        /**
         * id : 296283
         * uid : 1028489
         * name :
         * title : 定海桥：停跳的
         上海工业心脏
         * excerpt : 一开始拍摄时，让我尤其感到自己像个搅局者。但定海桥一点都没有见外的意思，这可能就是社区的强大生命力。
         * lead : 卡尔维诺认为，只有在某些时刻、某些街道上，人们才能看到一座城市最易混淆、罕见的，甚至是辉煌的事物。
         这一次，单读相册继续探寻城市，单读编辑 miu 来到曾作为老工业基地，如今却以都市“边缘”的身份而存在的——上海杨浦区定海桥，感受流淌于街巷角落、窗格护栏、楼梯扶手间的强大生命力量。
         * model : 1
         * position : 0
         * thumbnail : https://img.owspace.com/Public/uploads/Picture/2019-09-20/5d847a513b86c.jpeg
         * create_time : 1569112200
         * update_time : 2019/09/22
         * tags : [{"name":""}]
         * status : 1
         * video :
         * fm :
         * link_url :
         * publish_time : 0
         * view : 3682
         * share : http://static.owspace.com/wap/296283.html
         * comment : 1
         * good : 1
         * bookmark : 0
         * show_uid : 1028489
         * fm_play :
         * lunar_type : 1
         * hot_comments : []
         * html5 : http://static.owspace.com/wap/296283.html
         * author : 单读相册
         * tpl : 1
         * avatar : https://img.owspace.com/Public/static/avatar/2.png
         * discover : 0
         * category : TO READ
         * parseXML : 1
         */
        @Id
        private String id;
        private String title;
        private String author;
        private String avatar;


        @Generated(hash = 969262044)
        public DatasBean(String id, String title, String author, String avatar) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.avatar = avatar;
        }

        @Generated(hash = 128729784)
        public DatasBean() {
        }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public static class TagsBean {
            /**
             * name :
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

    @Override
    public String toString() {
        return "DatasBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}