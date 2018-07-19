package com.nervcv.utis_traffic_info_parser.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 소통정보 VO
 * 
 */
public class TrafInfoVO {

	public Date trafInfoCreatDate;
	public String linkID;
	public int speed;
	public int road_grade;
	public int congestion;
	public short pass_time;
	public int pattern_yn;

	public Date getTrafInfoCreatDate() {
		return trafInfoCreatDate;
	}

	public void setTrafInfoCreatDate(Date trafInfoCreatDate) {
		this.trafInfoCreatDate = trafInfoCreatDate;
	}

	public String getLinkID() {
		return linkID;
	}

	public void setLinkID(String linkID) {
		this.linkID = linkID;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getRoadGrade() {
		return road_grade;
	}

	public void setRoadGrade(int roadGrade) {
		this.road_grade = roadGrade;
	}

	public int getCongestion() {
		return congestion;
	}

	public void setCongestion(int congestion) {
		this.congestion = congestion;
	}

	public short getPass_time() {
		return pass_time;
	}

	public void setPass_time(short pass_time) {
		this.pass_time = pass_time;
	}

	public int getPattern_yn() {
		return pattern_yn;
	}

	public void setPattern_yn(int pattern_yn) {
		this.pattern_yn = pattern_yn;
	}

	@Override
	public String toString() {

		/*
		 * return "TrafInfoVO [trafInfoCreatDate=" + trafInfoCreatDate
		 * 
		 * + ", linkID=" + linkID + ", speed=" + speed + ", roadGrade=" + road_grade +
		 * ", congestion=" + congestion + ", pass_time=" + pass_time + ", pattern_yn=" +
		 * pattern_yn + "]";
		 * 
		 */
		return TXT_FileToString();

	}

	public String TXT_FileToString() {
		return changeToStringDate(trafInfoCreatDate) + "|" + linkID + "|" + speed + "|" + changeRoadGrade(road_grade)
				+ "|" + changeCongestion(congestion) + "|" + pass_time + "|" + pattern_yn;
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

	public String changePatternYN(int pattern_yn) {
		String parsingPatternYN = null;

		switch (pattern_yn) {
		case 0:
			parsingPatternYN = "실시간";
			break;
		case 1:
			parsingPatternYN = "패턴";
			break;
		default:
			parsingPatternYN = "존재하지 않는 코드";
		}
		return parsingPatternYN;
	}

	public String changeCongestion(int congestion) {
		String parsingCongestion = null;

		switch (congestion) {
		case 1:
			parsingCongestion = "원할";
			break;
		case 2:
			parsingCongestion = "지체";
			break;
		case 3:
			parsingCongestion = "정체";
			break;
		default:
			parsingCongestion = "존재하지 않는 코드";
		}
		return parsingCongestion;
	}

	private String changeToStringDate(Date date) {
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return simpleDate.format(date);
	}

}
