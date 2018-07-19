package com.nervcv.utis_traffic_info_parser.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.nervcv.utis_traffic_info_parser.UtisTrafficInfo;
import com.nervcv.utis_traffic_info_parser.util.FileParsingHelper;
import com.nervcv.utis_traffic_info_parser.vo.IncinfoVO;

public class InciInfoFileParse implements UtisTrafficInfo {

	private FileParsingHelper<IncinfoVO> parsingHelper;
	private File file;

	public InciInfoFileParse(String srcFilePath) throws Exception {
		this.parsingHelper = new FileParsingHelper<IncinfoVO>();
		this.file = parsingHelper.relativePathCheck_and_createFileObject(srcFilePath, true);
	}

	public InciInfoFileParse(String srcFilePath, boolean isRelativePath) throws Exception {
		this.parsingHelper = new FileParsingHelper<IncinfoVO>();
		this.file = parsingHelper.relativePathCheck_and_createFileObject(srcFilePath, isRelativePath);
	}

	public void parse(String descFilePath) throws Exception {

		parsingHelper.fileVerification(file, 4, 1179);

		FileInputStream fis = null;
		BufferedReader reader = null;

		fis = new FileInputStream(this.file);
		reader = new BufferedReader(new InputStreamReader(fis, "ISO-8859-1"));

		int inciTotalCount = step_1_totalParseCount_parse_and_read_4byte(reader);
		System.out.println(inciTotalCount);

		ArrayList<IncinfoVO> parseDataList = new ArrayList<IncinfoVO>();
		IncinfoVO vo;

		for (int i = 0; i < inciTotalCount; i++) {
			vo = new IncinfoVO();

			String inciId = step_2_inciId_parse_and_read_12byte(reader);
			vo.setInciID(inciId);
			;

			Date inciInfoCreatDate = step_3_inciInfoCreatDate_parse_and_read_14byte(reader);
			vo.setInciInfoCreatDate(inciInfoCreatDate);
			Date inciInfoStopDate = step_4_inciInfoStopDate_parse_and_read_14byte(reader);
			vo.setInciInfoStopDate(inciInfoStopDate);

			String linkId = step_5_linkId_parse_and_read_4byte(reader);
			vo.setLinkID(linkId);

			int road_grade = step_6_road_grade_parse_and_read_1byte(reader);
			vo.setRoad_grade(road_grade);

			int inciCode = step_7_inciCode_parse_and_read_2byte(reader);
			vo.setInciCode(inciCode);

			String inciTitle = step_8_inciTitle_parse_and_read_512byte(reader);
			vo.setInciTitle(inciTitle);

			String inciContent = step_9_inciContent_parse_and_read_512byte(reader);
			vo.setInciContent(inciContent);

			double pointX = step_10_pointX_parse_and_read_4byte(reader);
			vo.setPointX(pointX);

			double pointY = step_11_pointY_parse_and_read_4byte(reader);
			vo.setPointY(pointY);

			String roadName = step_12_roadName_parse_and_read_100byte(reader);
			vo.setRoadName(roadName);

			parseDataList.add(vo);
		}

		parsingHelper.convertToTextFile(parseDataList, descFilePath);
	}

	private int step_1_totalParseCount_parse_and_read_4byte(BufferedReader reader) throws IOException {
		int linkTotalCount = ((byte) reader.read() & 0xff) << 24 | ((byte) reader.read() & 0xff) << 16
				| ((byte) reader.read() & 0xff) << 8 | ((byte) reader.read() & 0xff);
		return linkTotalCount;
	}

	private String step_2_inciId_parse_and_read_12byte(BufferedReader reader) throws Exception {
		char[] charArray = new char[12];
		for (int i = 0; i < charArray.length; i++) {
			charArray[i] = (char) reader.read();
		}
		return new String(charArray);
	}

	private Date step_3_inciInfoCreatDate_parse_and_read_14byte(BufferedReader reader) throws Exception {
		char[] charArray = new char[14];
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		for (int i = 0; i < charArray.length; i++) {
			charArray[i] = (char) reader.read();
		}
		return dateFormat.parse(new String(charArray));
	}

	private Date step_4_inciInfoStopDate_parse_and_read_14byte(BufferedReader reader) throws Exception {
		char[] charArray = new char[14];
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		for (int i = 0; i < charArray.length; i++) {
			charArray[i] = (char) reader.read();
		}
		return dateFormat.parse(new String(charArray));
	}

	private String step_5_linkId_parse_and_read_4byte(BufferedReader reader) throws Exception {
		int linkId = ((byte) reader.read() & 0xff) << 24 | ((byte) reader.read() & 0xff) << 16
				| ((byte) reader.read() & 0xff) << 8 | ((byte) reader.read() & 0xff);
		return (linkId & 0xFFFFFFFFL) + ""; // int를, unsigned형 long으로 변환
	}

	private int step_6_road_grade_parse_and_read_1byte(BufferedReader reader) throws Exception {
		return reader.read();
	}

	private int step_7_inciCode_parse_and_read_2byte(BufferedReader reader) throws Exception {

		short shortValue = 0;
		byte[] byteArray = new byte[2];
		for (int i = 0; i < byteArray.length; i++) {
			byteArray[i] = (byte) reader.read();
		}

		shortValue |= (((short) byteArray[0]) << 8) & 0xFF00;
		shortValue |= (((short) byteArray[1])) & 0xFF;

		return (int) shortValue;
	}

	private String step_8_inciTitle_parse_and_read_512byte(BufferedReader reader) throws Exception {
		byte[] byteArray = new byte[512];
		for (int i = 0; i < byteArray.length; i++) {
			byteArray[i] = (byte) reader.read();
		}
		return new String(byteArray, "euc-kr");
	}

	private String step_9_inciContent_parse_and_read_512byte(BufferedReader reader) throws Exception {
		byte[] byteArray = new byte[512];
		for (int i = 0; i < byteArray.length; i++) {
			byteArray[i] = (byte) reader.read();
		}
		return new String(byteArray, "euc-kr");
	}

	private double step_10_pointX_parse_and_read_4byte(BufferedReader reader) throws Exception {
		return (double) (((byte) reader.read() & 0xff) << 24 | ((byte) reader.read() & 0xff) << 16
				| ((byte) reader.read() & 0xff) << 8 | ((byte) reader.read() & 0xff)) / 10000000;
	}

	private double step_11_pointY_parse_and_read_4byte(BufferedReader reader) throws Exception {
		return (double) (((byte) reader.read() & 0xff) << 24 | ((byte) reader.read() & 0xff) << 16
				| ((byte) reader.read() & 0xff) << 8 | ((byte) reader.read() & 0xff)) / 10000000;
	}

	private String step_12_roadName_parse_and_read_100byte(BufferedReader reader) throws Exception {
		byte[] byteArray = new byte[100];
		for (int i = 0; i < byteArray.length; i++) {
			byteArray[i] = (byte) reader.read();
		}
		return new String(byteArray, "euc-kr");
	}

}
