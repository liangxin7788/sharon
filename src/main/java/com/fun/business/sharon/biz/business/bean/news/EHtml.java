package com.fun.business.sharon.biz.business.bean.news;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class EHtml {

	interface Hide{}
    public interface Show{}

    private String title;// 标题

//    @JsonView(Hide.class)
    private String tit_url; // 详细
    
    @JsonView(Hide.class)
    private String detail_url;
    
    private int clicks; // 热度值
    
//    @JsonView(Hide.class)
    private String trend; // 热度趋势
	
}
