package com.fun.business.sharon.biz.business.controller;


import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.fun.business.sharon.biz.business.bean.HotTopic;
import com.fun.business.sharon.biz.business.bean.news.EHtml;
import com.fun.business.sharon.biz.business.bean.news.EHtmlList;
import com.fun.business.sharon.biz.business.service.HotTopicService;
import com.fun.business.sharon.common.GlobalResult;
import com.fun.business.sharon.common.OperateException;
import com.fun.business.sharon.utils.ObjectUtil;
import com.fun.business.sharon.utils.StringUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangxin
 * @since 2019-06-11
 */
@RestController
@RequestMapping("/hotTopic")
public class HotTopicController {

	@Autowired
	private RestTemplate initRestTemplate;
	
	@Autowired
	private HotTopicService hotTopicService;
	
	/**
	 * 1  今日实时热点     
	 * 257 今日互联网人物排行榜
	 * 258 今日热点人物排行榜 
	 * 342 今日社会民生事件  
	 * @throws UnsupportedEncodingException 
	 */
	@GetMapping("getNewsTopN")
	@ApiOperation("获取百度热点话题")
	public GlobalResult<?> getNewsTopN(@ApiParam("热点类型")@RequestParam(value = "type",required = true )Integer type,
			@ApiParam("前N条")@RequestParam(value = "topN",required = true )Integer topN
			) throws OperateException, UnsupportedEncodingException{
		String url = String.format("http://top.baidu.com/clip?b=%s", type);
		String html = initRestTemplate.getForObject(url, String.class);
		if (StringUtil.isNotEmpty(html)) {
			Pattern regex = Pattern.compile("((\\[\\{\"title).*?(}]))");
			Matcher matcher = regex.matcher(html);
			String result = "";
			while (matcher.find()) {
				result = matcher.group();
			}
			EHtmlList list = ObjectUtil.toObject(result, EHtmlList.class);
			if (list != null) {
				topN = list.size() > topN ? topN : list.size();
				List<EHtml> resultList = list.subList(0, topN);
				for (EHtml eHtml : resultList) {
					HotTopic hotTopic = new HotTopic();
					hotTopic.setNewsLink(eHtml.getTit_url());
					String title2 = eHtml.getTitle();

					hotTopic.setNewsTitle(title2);
					hotTopic.setNumber(eHtml.getClicks());
					hotTopic.setTrend("rise".equals(eHtml.getTrend()) ? true : false);
					hotTopic.setLoadAt(new Date());
					hotTopicService.save(hotTopic);
				}
				return GlobalResult.newSuccess(ObjectUtil.writeWithView(resultList, EHtml.Show.class));
			}
		}
		return GlobalResult.newError("获取热点信息失败！");
	}

}
