package com.biayna.bi.MQ;

import java.io.Serializable;

/**
 * Stores the file information, which is ready to be processed
 * @author Fred A.
 *	
 */
public class MLSHistoryDataFileInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String path;
	private String name;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
