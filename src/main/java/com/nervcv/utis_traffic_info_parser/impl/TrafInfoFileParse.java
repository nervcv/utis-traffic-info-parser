package com.nervcv.utis_traffic_info_parser.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.nervcv.utis_traffic_info_parser.UtisTrafficInfo;
import com.nervcv.utis_traffic_info_parser.util.FileParsingHelper;
import com.nervcv.utis_traffic_info_parser.vo.TrafInfoVO;

public class TrafInfoFileParse implements UtisTrafficInfo {

	private FileParsingHelper<TrafInfoVO> parsingHelper;
	private File file;

	public TrafInfoFileParse(String srcFilePath) throws Exception {
		this.parsingHelper = new FileParsingHelper<TrafInfoVO>();
		this.file = parsingHelper.relativePathCheck_and_createFileObject(srcFilePath, true);
	}

	public TrafInfoFileParse(String srcFilePath, boolean isRelativePath) throws Exception {
		this.parsingHelper = new FileParsingHelper<TrafInfoVO>();
		this.file = parsingHelper.relativePathCheck_and_createFileObject(srcFilePath, isRelativePath);
	}

	public void parse(String descFilePath) throws Exception {

		parsingHelper.fileVerification(file, 18, 10);

		FileInputStream fis = null;
		BufferedReader reader = null;

		fis = new FileInputStream(this.file);
		reader = new BufferedReader(new InputStreamReader(fis, "ISO-8859-1"));

		int linkTotalCount = step_1_totalParseCount_parse_and_read_4byte(reader);
		System.out.println(linkTotalCount);
		Date trafInfoCreatDate = step_2_trafInfoCreatDate_parse_and_read_14byte(reader);

		ArrayList<TrafInfoVO> parseDataList = new ArrayList<TrafInfoVO>();
		TrafInfoVO vo;

		for (int i = 0; i < linkTotalCount; i++) {
			vo = new TrafInfoVO();

			vo.setTrafInfoCreatDate(trafInfoCreatDate);

			String linkId = step_3_linkId_parse_and_read_4byte(reader);
			vo.setLinkID(linkId);

			int speed = step_4_speed_parse_and_read_1byte(reader);
			vo.setSpeed(speed);

			int road_grade = step_5_road_graed_parse_and_read_1byte(reader);
			vo.setRoadGrade(road_grade);

			int congestion = step_6_congestion_parse_and_read_1byte(reader);

			vo.setCongestion(congestion);

			short pass_time = step_7_pass_time_parse_and_read_2byte(reader);
			vo.setPass_time(pass_time);

			int pattern_yn = step_8_pattern_yn_parse_and_read_1byte(reader);
			vo.setPattern_yn(pattern_yn);

			parseDataList.add(vo);
		}

		parsingHelper.convertToTextFile(parseDataList, descFilePath);
	}

	private int step_1_totalParseCount_parse_and_read_4byte(BufferedReader reader) throws IOException {
		// case-1

		// int linkTotalCount = ((byte)reader.read() & 0xff)<<24 | ((byte)reader.read()
		// & 0xff)<<16 | ((byte)reader.read() & 0xff)<<8 | ((byte)reader.read() & 0xff);
		// return linkTotalCount;

		// case-2
		byte[] byteArray = new byte[4];

		for (int i = 0; i < byteArray.length; i++) {
			byteArray[i] = (byte) reader.read();
		}

		ByteBuffer buff = ByteBuffer.allocate(Integer.SIZE / 8);
		buff.order(ByteOrder.BIG_ENDIAN);

		// buff사이즈는 4인 상태임
		// bytes를 put하면 position과 limit는 같은 위치가 됨.
		buff.put(byteArray);

		// flip()가 실행 되면 position은 0에 위치 하게 됨.
		buff.flip();

		return buff.getInt(); // position위치(0)에서 부터 4바이트를 int로 변경하여 반환
	}

	private Date step_2_trafInfoCreatDate_parse_and_read_14byte(BufferedReader reader) throws Exception {
		char[] charArray = new char[14];
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		for (int i = 0; i < charArray.length; i++) {
			charArray[i] = (char) reader.read();
		}
		return dateFormat.parse(new String(charArray));
	}

	private String step_3_linkId_parse_and_read_4byte(BufferedReader reader) throws Exception {
		int linkId = ((byte) reader.read() & 0xff) << 24 | ((byte) reader.read() & 0xff) << 16
				| ((byte) reader.read() & 0xff) << 8 | ((byte) reader.read() & 0xff);
		return (linkId & 0xFFFFFFFFL) + ""; // int를, unsigned형 long으로 변환

	}

	private int step_4_speed_parse_and_read_1byte(BufferedReader reader) throws Exception {
		return reader.read();
	}

	private int step_5_road_graed_parse_and_read_1byte(BufferedReader reader) throws Exception {
		return reader.read();
	}

	private int step_6_congestion_parse_and_read_1byte(BufferedReader reader) throws Exception {
		return reader.read();
	}

	private short step_7_pass_time_parse_and_read_2byte(BufferedReader reader) throws Exception {
		short shortValue = 0;
		byte[] byteArray = new byte[2];
		for (int i = 0; i < byteArray.length; i++) {
			byteArray[i] = (byte) reader.read();
		}
		shortValue |= (((short) byteArray[0]) << 8) & 0xFF00;
		shortValue |= (((short) byteArray[1])) & 0xFF;
		return shortValue;
	}

	private int step_8_pattern_yn_parse_and_read_1byte(BufferedReader reader) throws Exception {
		return reader.read();
	}
}
