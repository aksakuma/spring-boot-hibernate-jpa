package jp.co.maxa.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.maxa.com.model.master.ProjectMaster;
import jp.co.maxa.com.service.MasterService;

/**
 * サンプルコントローラーを表現します。
 */
@RestController
@RequestMapping("/api/master")
public class MasterController extends BaseController {

	@Autowired
	MasterService service;

	@GetMapping("/getItems")
	public List<ProjectMaster> getItems(){
		return service.getItems();
	}
}