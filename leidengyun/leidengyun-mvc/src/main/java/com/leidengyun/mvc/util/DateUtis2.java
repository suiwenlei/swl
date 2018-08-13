package com.leidengyun.mvc.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;




public class DateUtis2 {
	public static TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
	  public static SimpleDateFormat sdfDate0 = new SimpleDateFormat("yyyy-MM-dd");
	  public static SimpleDateFormat sdfDate1 = new SimpleDateFormat("yyyyMMdd");
	  public static SimpleDateFormat sdfDate2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  public static SimpleDateFormat sdfDate20 = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
	  public static SimpleDateFormat sdfDate3 = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	  public static SimpleDateFormat sdfDate4 = new SimpleDateFormat("MM-dd HH:mm");
	  public static SimpleDateFormat sdfDate5 = new SimpleDateFormat("yyMMddHHmmss");
	  public static SimpleDateFormat sdfDate6 = new SimpleDateFormat("HHmm");
	  public static SimpleDateFormat sdfDate7 = new SimpleDateFormat("dd");
	  public static SimpleDateFormat sdfDate8 = new SimpleDateFormat("HH");
	  public static SimpleDateFormat sdfDate9 = new SimpleDateFormat("yyyy年MM月dd日 E");
	  public static SimpleDateFormat sdfDate10 = new SimpleDateFormat("yyyy年MM月dd日");
	  public static SimpleDateFormat sdfDate11 = new SimpleDateFormat("yyyyMMddHHmmss");
	  public static SimpleDateFormat sdfDate12 = new SimpleDateFormat("yyyyMM");
	  public static SimpleDateFormat sdfDate13 = new SimpleDateFormat("yyyyMMdd HHmmss");
	  public static SimpleDateFormat sdfDate14 = new SimpleDateFormat("yyyyMMdd HHmm");
	  public static SimpleDateFormat sdfDate15 = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
	  public static SimpleDateFormat sdfDate16 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	  public static SimpleDateFormat sdfDate17 = new SimpleDateFormat("MM月dd日");
	  public static SimpleDateFormat sdfDate18 = new SimpleDateFormat("yyyyMMddHHmm");
	  public static SimpleDateFormat sdfDate19 = new SimpleDateFormat("MM");
	  
	  public void DateUtils()
	  {
	    sdfDate0.setTimeZone(tz);
	    sdfDate1.setTimeZone(tz);
	    sdfDate2.setTimeZone(tz);
	    sdfDate3.setTimeZone(tz);
	    sdfDate4.setTimeZone(tz);
	    sdfDate5.setTimeZone(tz);
	    sdfDate6.setTimeZone(tz);
	    sdfDate7.setTimeZone(tz);
	    sdfDate8.setTimeZone(tz);
	    sdfDate9.setTimeZone(tz);
	    sdfDate10.setTimeZone(tz);
	    sdfDate11.setTimeZone(tz);
	    sdfDate12.setTimeZone(tz);
	    sdfDate13.setTimeZone(tz);
	    sdfDate14.setTimeZone(tz);
	    sdfDate15.setTimeZone(tz);
	    sdfDate16.setTimeZone(tz);
	    sdfDate17.setTimeZone(tz);
	    sdfDate18.setTimeZone(tz);
	    sdfDate19.setTimeZone(tz);
	    sdfDate20.setTimeZone(tz);
	  }
	  
	  public static String getTimeInMillis(String strDate, String strStatus)
	  {
	    if ((strDate.equals("")) || (strDate.isEmpty()) || (strDate == null)) {
	      return "0";
	    }
	    String[] strTemp = strDate.split("-", 0);
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeZone(tz);
	    cal.clear();
	    if (strStatus.equals("begin")) {
	      cal.set(Integer.valueOf(strTemp[0]).intValue(), Integer.valueOf(strTemp[1]).intValue() - 1, Integer.valueOf(strTemp[2]).intValue(), 0, 0, 0);
	    }
	    if (strStatus.equals("end")) {
	      cal.set(Integer.valueOf(strTemp[0]).intValue(), Integer.valueOf(strTemp[1]).intValue() - 1, Integer.valueOf(strTemp[2]).intValue(), 23, 59, 59);
	    }
	    return String.valueOf(cal.getTimeInMillis());
	  }
	  
	  public static java.util.Date getDate(SimpleDateFormat sdf, String time)
	  {
	    time = time.trim();
	    try
	    {
	      return sdf.parse(time);
	    }
	    catch (Exception ex) {}
	    return null;
	  }
	  
	  public static String getAddDate(java.util.Date date, int addDay)
	  {
	    Calendar calendar = Calendar.getInstance(Locale.CHINA);
	    calendar.setTime(date);
	    calendar.add(5, addDay);
	    return String.valueOf(calendar.getTimeInMillis());
	  }
	  
	  public static String getTimeInFormat1(String strDate)
	  {
	    if ((strDate.equals("")) || (strDate.isEmpty()) || (strDate == null)) {
	      return "0";
	    }
	    String[] strTemp = strDate.split("-", 0);
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeZone(tz);
	    cal.clear();
	    cal.set(Integer.valueOf(strTemp[0]).intValue(), Integer.valueOf(strTemp[1]).intValue() - 1, Integer.valueOf(strTemp[2]).intValue(), 23, 59, 59);
	    return sdfDate1.format(cal.getTime());
	  }
	  
	  public static String getTimeInFormat(String strDate)
	  {
	    if ((strDate.equals("")) || (strDate.isEmpty()) || (strDate == null) || (strDate.length() != 8)) {
	      return "0";
	    }
	    String[] strTemp = new String[3];
	    strTemp[0] = strDate.substring(0, 4);
	    strTemp[1] = strDate.substring(4, 6);
	    strTemp[2] = strDate.substring(6, 8);
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeZone(tz);
	    cal.clear();
	    cal.set(Integer.valueOf(strTemp[0]).intValue(), Integer.valueOf(strTemp[1]).intValue() - 1, Integer.valueOf(strTemp[2]).intValue(), 23, 59, 59);
	    return sdfDate0.format(cal.getTime());
	  }
	  
	  public static String getFormatCalendar(String strTimeMillis)
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeZone(tz);
	    cal.setTimeInMillis(Long.valueOf(strTimeMillis).longValue());
	    return sdfDate0.format(cal.getTime());
	  }
	  
	  public static String getFormatCalendarByModel(SimpleDateFormat model, String strTimeMillis)
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeZone(tz);
	    cal.setTimeInMillis(Long.valueOf(strTimeMillis).longValue());
	    return model.format(cal.getTime());
	  }
	  
	  public static java.util.Date getMinOfCurrentMonth(java.util.Date date)
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeZone(tz);
	    cal.setTime(date);
	    cal.set(5, cal.getMinimum(5));
	    return cal.getTime();
	  }
	  
	  public static java.util.Date getMaxOfCurrentMonth(java.util.Date date)
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeZone(tz);
	    cal.setTime(date);
	    cal.set(5, cal.getActualMaximum(5));
	    return cal.getTime();
	  }
	  
	  public static java.util.Date getMinOfNextMonth(java.util.Date date)
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeZone(tz);
	    cal.setTime(date);
	    cal.set(2, cal.get(2) + 1);
	    cal.set(5, cal.getMinimum(5));
	    return cal.getTime();
	  }
	  
	  public static java.util.Date getMaxOfNextMonth(java.util.Date date)
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeZone(tz);
	    cal.setTime(date);
	    cal.set(5, 1);
	    cal.set(2, cal.get(2) + 1);
	    cal.set(5, cal.getActualMaximum(5));
	    return cal.getTime();
	  }
	  
	  public static String getNextDate(String format)
	  {
	    SimpleDateFormat sdf = new SimpleDateFormat(format);
	    Calendar c = Calendar.getInstance();
	    c.add(5, 1);
	    String s_date = sdf.format(c.getTime());
	    return s_date;
	  }
	  
	  public static java.util.Date getDate(int year, int month, int days)
	  {
	    Calendar calendar = Calendar.getInstance(Locale.CHINA);
	    calendar.set(year, month - 1, days, 0, 0, 0);
	    return calendar.getTime();
	  }
	  
	  public static String getStringOfOffsetDate(SimpleDateFormat sdf, String date, long offset)
	  {
	    long temp = getDate(sdf, date).getTime();
	    return sdf.format(new java.util.Date(temp + offset * 86400L * 1000L));
	  }
	  
	  public static java.util.Date getDateFromSimpleDateFormatString(SimpleDateFormat sdf, String date)
	  {
	    return getDate(sdf, date);
	  }
	  
	  public static void main(String[] args)
	  {
	    java.util.Date now = new java.util.Date();
	    Calendar cal = Calendar.getInstance();
	    cal.set(2, cal.get(2) + 1);
	    System.out.println(sdfDate2.format(cal.getTime()));
	    System.out.println(5);
	    System.out.println(cal.getMaximum(5));
	    System.out.println("当前日期：" + sdfDate3.format(now));
	    System.out.println("本月第一天：" + sdfDate3.format(getMinOfCurrentMonth(now)));
	    System.out.println("本月最后一天：" + sdfDate3.format(getMaxOfCurrentMonth(now)));
	    System.out.println("下月第一天：" + sdfDate3.format(getMinOfNextMonth(now)));
	    System.out.println("下月最后一天：" + sdfDate3.format(getMaxOfNextMonth(now)));
	  }
	  
	  public static String getCurrDateStr()
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.set(2, cal.get(2) + 1);
	    
	    return sdfDate2.format(cal.getTime());
	  }
	  
	  public static String getCurDateStr()
	  {
	    Calendar cal = Calendar.getInstance();
	    return sdfDate2.format(cal.getTime());
	  }
	  
	 
	  
	  public static long getQuot(String time1, String time2)
	  {
	    long quot = 0L;
	    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
	    try
	    {
	      java.util.Date date1 = ft.parse(time1);
	      java.util.Date date2 = ft.parse(time2);
	      quot = date1.getTime() - date2.getTime();
	      quot = quot / 1000L / 60L / 60L / 24L;
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	    return quot;
	  }
	  
	  public static java.util.Date getMaxDate()
	  {
	    return getDate(sdfDate0, "2099-12-30");
	  }
	  
	  public static java.util.Date getMinDate()
	  {
	    return getDate(sdfDate0, "1970-12-30");
	  }
	  
	  public static String getCurrDateStrOfYYYMMDDHHMMSS()
	  {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyMMddHHmmss");
	    return sdf.format(new java.util.Date());
	  }
	  
	  public static String getCurrDateStrOfYYYYMMDD()
	  {
	    return getDateStrOfYYYYMMDD(new java.util.Date());
	  }
	  
	  public static String getCurrDateStrOfYYYYMM()
	  {
	    String currDate = getDateStrOfYYYYMMDD(new java.util.Date());
	    return currDate.substring(0, 6);
	  }
	  
	  public static String getDateStrOfYYYYMMDD(java.util.Date date)
	  {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    
	    return sdf.format(date);
	  }
	  
	  public static String getDateStrOfYYYY_MM_DD(java.util.Date date)
	  {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    
	    return sdf.format(date);
	  }
	  
	  public static String getDateStrOfYYYMMDDHHMMSS(java.util.Date date)
	  {
	    if (date == null) {
	      return null;
	    }
	    return sdfDate2.format(date);
	  }
	  
	  
	  public static java.util.Date getdate(String date)
	  {
	    java.util.Date d = null;
	    try
	    {
	      if ((date != null) && (date.length() > 19)) {
	        date = date.substring(0, 19);
	      }
	      if ((date != null) && (date.length() == 19)) {
	        d = sdfDate2.parse(date);
	      } else if ((date != null) && (date.length() == 13)) {
	        d = sdfDate14.parse(date);
	      } else if ((date != null) && (date.length() == 16)) {
	        d = sdfDate16.parse(date);
	      } else if ((date != null) && (date.length() == 17)) {
	        d = sdfDate3.parse(date);
	      } else if ((date != null) && (date.length() == 14)) {
	        d = sdfDate11.parse(date);
	      } else if ((date != null) && (date.length() == 8)) {
	        d = sdfDate1.parse(date);
	      } else if ((date != null) && (date.length() == 10)) {
	        d = sdfDate0.parse(date);
	      }
	    }
	    catch (Exception e)
	    {
	      d = null;
	    }
	    return d;
	  }
	  
	  
	  public static int getCurHour(java.util.Date date)
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeZone(tz);
	    cal.setTime(date == null ? new java.util.Date() : date);
	    
	    return cal.get(11);
	  }
	  
	  public static java.util.Date addDate(java.util.Date date, int field, int amount)
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeZone(tz);
	    cal.setTime(date);
	    cal.add(field, amount);
	    
	    return cal.getTime();
	  }
	  
	  public static java.util.Date getCurDate()
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeZone(tz);
	    return cal.getTime();
	  }
	  
	  public static String date2String(java.util.Date date, String format)
	  {
	    if (date == null) {
	      return "";
	    }
	    SimpleDateFormat sdf = new SimpleDateFormat(format);
	    String date_str = "";
	    try
	    {
	      date_str = sdf.format(date);
	    }
	    catch (Exception localException) {}
	    return date_str;
	  }
	  
	  public static boolean compareDate(java.util.Date date1, java.util.Date date2)
	  {
	    boolean bl = false;
	    double d = date1.getTime() - date2.getTime();
	    if (d > 0.0D) {
	      return true;
	    }
	    return bl;
	  }
	  
	  public static java.util.Date getCurDayOfLastYear(java.util.Date date)
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeZone(tz);
	    cal.setTime(date);
	    cal.add(1, -1);
	    return cal.getTime();
	  }
	  
	  public static java.util.Date getCurDayOfLastMonth(java.util.Date date)
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeZone(tz);
	    cal.setTime(date);
	    cal.add(2, -1);
	    return cal.getTime();
	  }
}
