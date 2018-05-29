package pers.ken.ssm.po;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import pers.ken.ssm.controller.validation.ValidGroup1;
import pers.ken.ssm.controller.validation.ValidGroup2;

/**
 * @desc 简单po类 Items
 * @author ken
 *
 */
public class Items {
    private Integer id;

    //长度检验
    @Size(min=1, max=30, message="{items.name.length.error}", groups={ValidGroup1.class})
    private String name;

    private Float price;

    private String pic;

    //非空校验
    @NotNull(message="{items.createtime.isNUll}", groups={ValidGroup2.class})
    private Date createtime;

    @Size(min=1, max=100, message="{items.detail.length.error}", groups={ValidGroup1.class})
    private String detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}