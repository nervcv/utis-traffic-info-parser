package com.nervcv.utis_traffic_info_parser.vo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 돌발정보 VO
 * 
 */
public class IncinfoVO {

	public String inciID;
	public Date inciInfoCreatDate;
	public Date inciInfoStopDate;
	public String linkID;
	public int road_grade;
	public int inciCode;
	public String inciTitle;
	public String inciContent;
	public double pointX;
	public double pointY;
	public String roadName;

	public String getInciID() {
		return inciID;
	}

	public void setInciID(String inciID) {
		this.inciID = inciID;
	}

	public Date getInciInfoCreatDate() {
		return inciInfoCreatDate;
	}

	public void setInciInfoCreatDate(Date inciInfoCreatDate) {
		this.inciInfoCreatDate = inciInfoCreatDate;
	}

	public Date getInciInfoStopDate() {
		return inciInfoStopDate;
	}

	public void setInciInfoStopDate(Date inciInfoStopDate) {
		this.inciInfoStopDate = inciInfoStopDate;
	}

	public String getLinkID() {
		return linkID;
	}

	public void setLinkID(String linkID) {
		this.linkID = linkID;
	}

	public int getRoad_grade() {
		return road_grade;
	}

	public void setRoad_grade(int road_grade) {
		this.road_grade = road_grade;
	}

	public int getInciCode() {
		return inciCode;
	}

	public void setInciCode(int inciCode) {
		this.inciCode = inciCode;
	}

	public String getInciTitle() {
		return inciTitle;
	}

	public void setInciTitle(String inciTitle) {
		this.inciTitle = inciTitle;
	}

	public String getInciContent() {
		return inciContent;
	}

	public void setInciContent(String inciContent) {
		this.inciContent = inciContent;
	}

	public double getPointX() {
		return pointX;
	}

	public void setPointX(double pointX) {
		this.pointX = pointX;
	}

	public double getPointY() {
		return pointY;
	}

	public void setPointY(double pointY) {
		this.pointY = pointY;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	@Override
	public String toString() {
		/*
		 * return "IncinfoVO [inciID=" + inciID + ", inciInfoCreatDate=" +
		 * inciInfoCreatDate + ", inciInfoStopDate=" + inciInfoStopDate + ", linkID=" +
		 * linkID + ", road_grade=" + road_grade + ", inciCode=" + inciCode +
		 * ", inciTitle=" + inciTitle + ", inciContent=" + inciContent + ", pointX=" +
		 * pointX + ", pointY=" + pointY + ", roadName=" + roadName + "]";
		 */
		return TXT_FileToString();
	}

	private String TXT_FileToString() {
		return inciID + "|" + changeToStringDate(inciInfoCreatDate) + "|" + changeToStringDate(inciInfoStopDate) + "|"
				+ linkID + "|" + changeRoadGrade(road_grade) + "|" + changeInciCode(inciCode) + "|" + inciTitle + "|"
				+ inciContent + "|" + pointX + "|" + pointY + "|" + roadName;
	}

	public String changeRoadGrade(int roadGrade) {
		String parsingRoadGrade = null;

		switch (roadGrade) {
		case 1:
			parsingRoadGrade = "고속도로";
			break;
		case 2:
			parsingRoadGrade = "도시고속도로";
			break;
		case 3:
			parsingRoadGrade = "일반국도";
			break;
		case 4:
			parsingRoadGrade = "특별·광역시도";
			break;
		case 5:
			parsingRoadGrade = "국가지원지방도";
			break;
		case 6:
			parsingRoadGrade = "지방도";
			break;
		case 7:
			parsingRoadGrade = "시·군도";
			break;
		default:
			parsingRoadGrade = "존재하지 않는 코드";
		}

		return parsingRoadGrade;

	}

	public String changeInciCode(int inciCode) {

		int[] binaryRepresentation = new int[16];
		// bitArray
		for (int i = 0; i < 16; i++) {
			binaryRepresentation[i] = inciCode % 2;
			inciCode = inciCode / 2;
		}

		ArrayList<String> resultList = new ArrayList<String>();

		if (binaryRepresentation[0] == 1)
			resultList.add("");
		if (binaryRepresentation[1] == 1)
			resultList.add("");
		if (binaryRepresentation[2] == 1)
			resultList.add("차량사고(사고)");
		if (binaryRepresentation[3] == 1)
			resultList.add("기상관련 사고");
		if (binaryRepresentation[4] == 1)
			resultList.add("기후, 고장 등으로 인한 차량의 정지(고장)");
		if (binaryRepresentation[5] == 1)
			resultList.add("차량화재");
		if (binaryRepresentation[6] == 1)
			resultList.add("장애물");
		if (binaryRepresentation[7] == 1)
			resultList.add("위험물 방출");
		if (binaryRepresentation[8] == 1)
			resultList.add("지진(기상)");
		if (binaryRepresentation[9] == 1)
			resultList.add("산사태(대표=기상)");
		if (binaryRepresentation[10] == 1)
			resultList.add("홍수(강우=기상)");
		if (binaryRepresentation[11] == 1)
			resultList.add("태풍(강풍=기상)");
		if (binaryRepresentation[12] == 1)
			resultList.add("예고되지 않은 시위 또는 집회(행사)");
		if (binaryRepresentation[13] == 1)
			resultList.add("차량의 급격한 증가(공사)");
		if (binaryRepresentation[14] == 1)
			resultList.add("구분없음");
		if (binaryRepresentation[15] == 1)
			resultList.add("");

		return resultList.toString();
	}

	private String changeToStringDate(Date date) {
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return simpleDate.format(date);
	}

}
