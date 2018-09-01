package com.beike.wechat.pojo;

import java.util.ArrayList;
import java.util.List;

public class CheckInRequest {

	private int opencheckindatatype;

	private long starttime;

	private long endtime;

	private List<String> useridlist = new ArrayList<String>();

	public int getOpencheckindatatype() {
		return opencheckindatatype;
	}

	public void setOpencheckindatatype(int opencheckindatatype) {
		this.opencheckindatatype = opencheckindatatype;
	}

	public long getStarttime() {
		return starttime;
	}

	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}

	public long getEndtime() {
		return endtime;
	}

	public void setEndtime(long endtime) {
		this.endtime = endtime;
	}

	public List<String> getUseridlist() {
		return useridlist;
	}

	public void setUseridlist(List<String> useridlist) {
		this.useridlist = useridlist;
	}
	

}
