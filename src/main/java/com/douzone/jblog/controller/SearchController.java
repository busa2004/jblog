package com.douzone.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private PostService postService;
	
	@RequestMapping("")
	public String search(BlogVo blogVo,Model model) {
		
		List<BlogVo> blogVoList = null;
		List<PostVo> postVoList = null;
		
		if(( blogVo.getWhich() == null)) {
			postVoList = postService.searchPost(blogVo);
		} else if (blogVo.getWhich().equals("blog-title")) {
			blogVoList = blogService.searchTitle(blogVo);
		} else if (blogVo.getWhich().equals("blog-user")) {
			blogVoList = blogService.searchUser(blogVo);
		} else if (blogVo.getWhich().equals("tag"))  {
			postVoList = postService.searchPost(blogVo);
		} else {
			
		}
		
		System.out.println();
		System.out.println(postVoList);
		System.out.println(blogVoList);
		System.out.println();
		
		if(blogVo.getKeyword().equals("") ||
			(blogVoList==null && postVoList==null) ||
			(blogVoList==null && postVoList.isEmpty())||
			(postVoList==null && blogVoList.isEmpty())) {
			postVoList = null;
			blogVoList = null;
		}
		
		model.addAttribute("postVo", postVoList);
		model.addAttribute("blogVo", blogVoList);
		
		return "search/index";
	}
}
