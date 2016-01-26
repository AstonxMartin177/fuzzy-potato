package datedistance;

/**
 *
 * @author J Goocher
 */
public class DateDistance {

    public static void main(String[] args) {
        long month0 = Long.parseLong( args[0] );
        long day0 = Long.parseLong( args[1] );
        long year0 = Long.parseLong( args[2] );
        long month1 = Long.parseLong( args[3] );
        long day1 = Long.parseLong( args[4] );
        long year1 = Long.parseLong( args[5] );
        System.out.println(distance(month0, day0, year0, month1, day1, year1));
    }
    public static boolean isCommonYear(long year){        
        if(year%4==0){
            if(year%100==0){
                return !(year%400==0);
            } else{
                return true;
            }
        } else {
            return true;
        }
    }
    public static long monthLength(long month, long year){
        long daysInMonth = 0;
        int i = (int) month;
        switch (i){
            case 1: daysInMonth = 31;
                    break;
            case 2: daysInMonth = isCommonYear(year)? 28 : 29;
                    break;
            case 3: daysInMonth = 31;
                    break;
            case 4: daysInMonth = 30;
                    break;
            case 5: daysInMonth = 31;
                    break;
            case 6: daysInMonth = 30;
                    break;
            case 7: daysInMonth = 31;
                    break;
            case 8: daysInMonth = 31;
                    break;
            case 9: daysInMonth = 30;
                    break;
            case 10: daysInMonth = 31;
                    break;
            case 11: daysInMonth = 30;
                    break;
            case 12: daysInMonth = 31;
        }
        return daysInMonth;
    }
    public static boolean isRealDate(long month, long day, long year){
        return isRealMonth(month) && isRealDay(month, day, year) && isRealYear(year);
    }
    public static boolean isRealMonth(long month){
        return 1 <= month && month <= 12;
    }
    public static boolean isRealDay(long month, long day, long year){
        return 1 <= day && day <= monthLength(month, year);
    }
    public static boolean isRealYear(long year){
        return 1 <= year;
    }
    public static long distance(long month0, long day0, long year0, long month1, long day1, long year1){
        long daysApart = 0;
        if(isRealDate(month0, day0, year0)&&isRealDate(month1, day1, year1)){
            if(year0 > year1){
                daysApart = distanceBetween(month0, day0, year0, month1, day1, year1);
            }else if(year0 < year1){
                daysApart = distanceBetween(month1, day1, year1, month0, day0, year0);
            } else if(year0 == year1){
                if(month0 > month1){
                    daysApart = distanceBetween(month0, day0, year0, month1, day1, year1);
                }else if(month0 < month1){
                    daysApart = distanceBetween(month1, day1, year1, month0, day0, year0);
                } else if(month0 == month1){
                    if(day0 > day1){
                        daysApart = distanceBetween(month0, day0, year0, month1, day1, year1);
                    }else if(day0 < day1){
                        daysApart = distanceBetween(month1, day1, year1, month0, day0, year0);
                    } else if(day0 == day1){
                        daysApart = 0;
                    }
                }
            }
        } else{
            return -1;
        }
        return daysApart;
    }
    public static long distanceBetween(long month0, long day0, long year0, long month1, long day1, long year1){
        long daysApart = 0;
        for(long i = 0; i <= year0 - year1 - 1; i++){
            daysApart = isCommonYear(year1+i) ? 365+daysApart : 366+daysApart;
        }
        for(long x = 1; x < month0; x++){
            daysApart = monthLength(x, year0) + daysApart;
        }
        for(long y = 1; y < month1; y++){
            daysApart = daysApart - monthLength(y, year1);
        }
        daysApart = daysApart + day0 - day1;
        return daysApart;
    }
}
