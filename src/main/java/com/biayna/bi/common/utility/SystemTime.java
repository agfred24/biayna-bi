package com.biayna.bi.common.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemTime {
	public static String getCurrentSystemTime() {
		long systemCurrentmilliseconds = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd-HH.mm.s.S.Z");    
		Date uploadDate = new Date(systemCurrentmilliseconds);
		//returns current System Date Time
		return sdf.format(uploadDate);
	}
}
