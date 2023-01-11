package com.modubiz.modu.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static final SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
	private static final SimpleDateFormat yyMMdd = new SimpleDateFormat("yyMMdd");
	private static final SimpleDateFormat HHmmss = new SimpleDateFormat("HHmmss");
	private static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	private static final SimpleDateFormat MMddHHmmss = new SimpleDateFormat("MMddHHmmss");
	private static final SimpleDateFormat yyMMddHHmmss = new SimpleDateFormat("yyMMddHHmmss");
	private static final SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final SimpleDateFormat sdfDay = new SimpleDateFormat("E");
	private static SimpleDateFormat reqFormat = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat resFormat = new SimpleDateFormat("yyyyMMdd");



	/*
	 * 현재일자를 조회한다.
	 * 
	 * @return String - 'yyyyMMdd' 형식
	 */
	public static String getCurDt() {
		Calendar cal = Calendar.getInstance();
		return yyyyMMdd.format(cal.getTime());
	}

	public static String getCurDt(Date cal) {
		return yyyyMMdd.format(cal);
	}

	/*
	 * 현재요일을 조회한다.
	 * 
	 * @return String - 'yyyyMMdd' 형식
	 */
	public static int getCurDay() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/*
	 * 현재시각을 조회한다.
	 * 
	 * @return String - 'HHmmss' 형식
	 */
	public static String getCurTm() {
		Calendar cal = Calendar.getInstance();
		return HHmmss.format(cal.getTime());
	}

	public static String getCurTm(Date cal) {
		return HHmmss.format(cal);
	}

	/*
	 * 현재일시를 조회한다.
	 * 
	 * @return String - 'yyyyMMddHHmmss' 형식
	 */
	public static String getCurTime() {
		Calendar cal = Calendar.getInstance();
		return yyyyMMddHHmmss.format(cal.getTime());
	}

	public static String getCurTime(Date cal) {
		return yyyyMMddHHmmss.format(cal);
	}

	/**
	 * get MMddHHmmss Format
	 * 
	 * @return String
	 */
	public static final String getMMddHHmmss(Date date) {
		return MMddHHmmss.format(date);
	}

	/**
	 * get MMddHHmmss Format
	 * 
	 * @return String
	 */
	public static final String getMMddHHmmss() {
		return MMddHHmmss.format(new Date());
	}

	/**
	 * get yyMMddHHmmss Format
	 * 
	 * @return String
	 */
	public static final String getyyMMddHHmmss(Date date) {
		return yyMMddHHmmss.format(date);
	}

	/**
	 * get yyMMddHHmmss Format
	 * 
	 * @return String
	 */
	public static final String getyyMMddHHmmss() {
		return yyMMddHHmmss.format(new Date());
	}

	/**
	 * getyyMMdd
	 * 
	 * @param date
	 * @return String
	 */
	public static final String getyyMMdd(Date date) {
		return yyMMdd.format(date);
	}

	/**
	 * getyyMMdd
	 * 
	 * @return String
	 */
	public static final String getyyMMdd() {
		return yyMMdd.format(new Date());
	}

	/*
	 * 현재일시를 조회한다.
	 * 
	 * @param UNIX TimeStamp (int)
	 * 
	 * @return String - 'yyyyMMddHHmmss' 형식
	 */
	public static String getStringDnt(int unixTime) {
		return getStringDnt(Long.valueOf(unixTime));
	}

	/*
	 * 현재일시를 조회한다.
	 * 
	 * @param UNIX TimeStamp (long)
	 * 
	 * @return String - 'yyyyMMddHHmmss' 형식
	 */
	public static String getStringDnt(long unixTime) {
		Calendar cal = Calendar.getInstance();
		return yyyyMMddHHmmss.format(new Date(unixTime * 1000));
	}

	/*
	 * 기준일의 요일을 조회한다.
	 * 
	 * @param String - 'yyyyMMdd' 형식
	 * 
	 * @return String - '월' 형식
	 */
	public static String getDayOfWeek(String dt) {
		Date tgtDt;
		try {
			tgtDt = yyyyMMdd.parse(dt);
		} catch (ParseException e) {
			return "";
		}
		return sdfDay.format(tgtDt);
	}

	/*
	 * 사용자형식으로 날짜 형식을 변환한다.
	 * 
	 * @param String - 대상날짜
	 * 
	 * @param String - 대상날짜의 형식
	 * 
	 * @param String - 변환날짜의 형식
	 * 
	 * @return String - 변환날짜형식의 값
	 */
	public static String getPatternDate(String dnt, String paramPtn, String rsltPtn) {
		reqFormat.applyPattern(paramPtn);
		resFormat.applyPattern(rsltPtn);

		Date tgtDt;
		try {
			tgtDt = reqFormat.parse(dnt);
		} catch (ParseException e) {
			return "";
		}
		return resFormat.format(tgtDt);
	}

	/*
	 * 날짜 연산 후 연산 후 날짜를 조회한다.
	 * 
	 * @param String - 대상날짜
	 * 
	 * @param int - 연산기준 (ex. Calendar.MONTH)
	 * 
	 * @param int - 연산 gap
	 * 
	 * @return String - 'yyyyMMdd' 형식의 연산 후 날짜
	 */
	public static String getCalDt(String dt, int type, int diff) {
		Calendar cal = Calendar.getInstance();
		Date tgtDt;
		try {
			tgtDt = yyyyMMdd.parse(dt);
		} catch (ParseException e) {
			return "";
		}
		cal.setTime(tgtDt);
		cal.add(type, diff);
		return yyyyMMdd.format(cal.getTime());
	}

	public static String getFirstDtOfMonth() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return formatter.format(cal.getTime());

	}

	public static String getTargetDay(int day) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, day);
		c.add(Calendar.DATE, 0);
		return formatter.format(c.getTime());

	}

	public static int doDiffOfDat(String curDate, String targetDate) {

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			Date beginDate = formatter.parse(curDate);
			Date endDate = formatter.parse(targetDate);

			long diff = endDate.getTime() - beginDate.getTime();
			long diffDays = diff / (24 * 60 * 60 * 1000);

			return (int) diffDays;

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * getDayBetween
	 * 
	 * @param date1
	 * @param date2
	 * @param format
	 * @return int
	 * @throws ParseException
	 */
	public static final int getTimesBetween(String date1, String date2, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date now = sdf.parse(date1);
		Date d = sdf.parse(date2);
		long v = now.getTime() - d.getTime();
		return (int) (v / (1000 * 60 ));
	}
	
	public static String getDtByFormat(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		return sdf.format(cal.getTime());
	}
}