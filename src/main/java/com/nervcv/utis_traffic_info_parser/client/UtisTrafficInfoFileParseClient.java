package com.nervcv.utis_traffic_info_parser.client;

import com.nervcv.utis_traffic_info_parser.UtisTrafficInfo;
import com.nervcv.utis_traffic_info_parser.impl.InciInfoFileParse;
import com.nervcv.utis_traffic_info_parser.impl.TrafInfoFileParse;

public class UtisTrafficInfoFileParseClient {

	public static void main(String[] args) throws Exception {

		UtisTrafficInfo traf_info = new TrafInfoFileParse("src/file/parse/traf/TRAF_INFO", true);
		traf_info.parse("C:\\TRAF_INFO.txt");

		UtisTrafficInfo inci_info = new InciInfoFileParse("src/file/parse/inci/INCI_INFO", true);
		inci_info.parse("C:\\INCI_INFO.txt");

	}
}
