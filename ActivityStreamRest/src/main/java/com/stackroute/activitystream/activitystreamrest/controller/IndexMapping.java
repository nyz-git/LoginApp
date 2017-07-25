package com.stackroute.activitystream.activitystreamrest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexMapping {
	@RequestMapping("/")
	public String hello()
	{
		return "index";
	}
}
