package com.example.buyeasy.bean;

/*  表示每一个用品对象*/
public class GoodsBean {
    private String pic;   //图片地址
    private String title;   //标题
    private String kind;    //种类
    private int count;     //库存
    private double price;   //价格
    private int buyCount = 1;  //购买数量

    public GoodsBean() {
    }

    public GoodsBean(String pic, String title, String kind, int count, double price, int buycount) {
        this.pic = pic;
        this.title = title;
        this.kind = kind;
        this.count = count;
        this.price = price;
        this.buyCount = buycount;
    }

    public GoodsBean(String pic, String title, String kind, int count, double price) {
        this.pic = pic;
        this.title = title;
        this.kind = kind;
        this.count = count;
        this.price = price;
    }

    public static GoodsBean copy(GoodsBean oldBean) {
        GoodsBean ib = new GoodsBean(oldBean.getPic(), oldBean.getTitle(), oldBean.getKind(),
                oldBean.getCount(), oldBean.getPrice());
        return ib;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
