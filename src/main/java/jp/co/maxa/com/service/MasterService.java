package jp.co.maxa.com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.maxa.com.model.master.ProjectMaster;

@Service
public class MasterService extends BaseService {

	public List<ProjectMaster> getItems(){
		return ProjectMaster.fildAll(repository());
	}
}
