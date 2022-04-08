package com.notegg.daybox;

public class CalculateDateDiff {

    public int GetDayInMonth(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else {
            if (year % 4 == 0) {
                if (year % 100 == 0) {
                    if (year % 400 == 0) return 29;
                    else return 28;
                }
                else return 29;
            }
            else return 28;
        }
    }

    public int GetDayInYear(int year) {
        int day = 0;
        for (int i=1; i<13; i++) {
            day += GetDayInMonth(year, i);
        }

        return day;
    }

    public int[] GetDiff(String target, String today) {
        int targetYear = Integer.parseInt(target.substring(0,4));
        int targetMonth = Integer.parseInt(target.substring(5,7));
        int targetDay = Integer.parseInt(target.substring(8));
        int todayYear = Integer.parseInt(today.substring(0,4));
        int todayMonth = Integer.parseInt(today.substring(5,7));
        int todayDay = Integer.parseInt(today.substring(8));

        if (target.equals(today)) return new int[] {-1, 0, 0}; // 오늘이 디데이인 경우

        if (targetYear > todayYear) { // 연도가 다른 경우
            int yearDiff = targetYear - todayYear;
            if (yearDiff > 1) { // 연도로만 봤을 때 2년 이상 차이나는 경우
                if (targetMonth > todayMonth) { // 실제로 yearDiff 년 이상의 차이인 경우
                    int monthDiff = targetMonth - todayMonth;
                    if (targetDay >= todayDay) { // 날짜가 같거나 이후인 경우 ( yearDiff 년 + monthDiff 개월 + XX일 )
                        return new int[] {yearDiff, monthDiff, targetDay - todayDay};
                    }
                    else { // 날짜가 이전인 경우 ( yearDiff 년 + (monthDiff - 1) 개월 + XX일 )
                        int todayToMonthEnd = GetDayInMonth(todayYear, todayMonth) - todayDay;
                        return new int[] {yearDiff, monthDiff - 1, todayToMonthEnd + targetDay};
                    }
                }
                else {
                    if (targetMonth == todayMonth) { // 같은 달인 경우
                        if (targetDay >= todayDay) { // 날짜가 같거나 이후인 경우 ( yearDiff 년 + 0개월 + XX일 )
                            return new int[] {yearDiff, 0, targetDay - todayDay};
                        }
                        else { // 날짜가 이전인 경우 ( (yearDiff - 1) 년 + 11개월 + XX일 )
                            int todayToMonthEnd = GetDayInMonth(todayYear, todayMonth) - todayDay;
                            return  new int[] {yearDiff - 1, 11, todayToMonthEnd + targetDay};
                        }
                    }
                    else { // 서로 다른 달일 경우
                        int months = (12 - todayMonth) + (targetMonth - 1);
                        if (targetDay >= todayDay) { // 날짜가 같거나 이후인 경우 ( (yearDiff - 1) 년 + (months + 1) 개월 + XX일 )
                            return new int[] {yearDiff - 1, months + 1, targetDay - todayDay};
                        }
                        else { // 날짜가 이전인 경우 ( (yearDiff - 1) 년 + months 개월 + XX일 )
                            int todayToMonthEnd = GetDayInMonth(todayYear, todayMonth) - todayDay;
                            return new int[] {yearDiff - 1, months, todayToMonthEnd + targetDay};
                        }
                    }
                }
            }
            else { // 연도로만 봤을 때 1년 차이인 경우
                if (targetMonth > todayMonth) { // 실제로 1년 이상의 차이인 경우
                    int monthDiff = targetMonth - todayMonth;
                    if (targetDay >= todayDay) { // 날짜가 같거나 이후인 경우 ( 1년 + monthDiff 개월 + XX일 )
                        return new int[] {1, monthDiff, targetDay - todayDay};
                    }
                    else { // 날짜가 이전인 경우 ( 1년 + (monthDiff - 1) 개월 + XX일 )
                        int todayToMonthEnd = GetDayInMonth(todayYear, todayMonth) - todayDay;
                        return new int[] {1, monthDiff - 1, todayToMonthEnd + targetDay};
                    }
                }
                else {
                    if (targetMonth == todayMonth) { // 같은 달인 경우
                        if (targetDay >= todayDay) { // 날짜가 같거나 이후인 경우 ( 1년 + 0개월 + XX일 )
                            return new int[] {1, 0, targetDay - todayDay};
                        }
                        else { // 날짜가 이전인 경우 ( 0년 + 11개월 + XX일 )
                            int todayToMonthEnd = GetDayInMonth(todayYear, todayMonth) - todayDay;
                            return  new int[] {0, 11, todayToMonthEnd + targetDay};
                        }
                    }
                    else { // 서로 다른 달일 경우
                        int months = (12 - todayMonth) + (targetMonth - 1);
                        if (targetDay >= todayDay) { // 날짜가 같거나 이후인 경우 ( 0년 + (months + 1) 개월 + XX일 )
                            return new int[] {0, months + 1, targetDay - todayDay};
                        }
                        else { // 날짜가 이전인 경우 ( 0년 + months 개월 + XX일 )
                            int todayToMonthEnd = GetDayInMonth(todayYear, todayMonth) - todayDay;
                            return new int[] {0, months, todayToMonthEnd + targetDay};
                        }
                    }
                }
            }
        }
        else { // 연도가 같은 경우
            if (targetMonth > todayMonth) { // 달이 다른 경우
                int monthDiff = targetMonth - todayMonth;
                if (monthDiff > 1) { // 달로만 봤을 때 2달 이상 차이나는 경우
                    if (targetDay >= todayDay) { // 날짜가 같거나 이후인 경우 ( monthDiff 개월 + XX일 )
                        return new int[] {0, monthDiff, targetDay - todayDay};
                    }
                    else { // 날짜가 이전인 경우 ( (monthDiff-1) 개월 + XX일 )
                        int todayToMonthEnd = GetDayInMonth(todayYear, todayMonth) - todayDay;
                        return new int[] {0, monthDiff - 1, todayToMonthEnd + targetDay};
                    }
                }
                else { // 달로만 봤을 때 한달 차이인 경우
                    if (targetDay >= todayDay) { // 날짜가 같거나 이후인 경우 ( 1개월 + XX일 )
                        return new int[] {0, 1, targetDay - todayDay};
                    }
                    else { // 날짜가 이전인 경우 ( 한달이 안되는 XX일 )
                        int todayToMonthEnd = GetDayInMonth(todayYear, todayMonth) - todayDay;
                        return new int[] {0, 0, todayToMonthEnd + targetDay};
                    }
                }
            }
            else { // 같은 년도 같은 달인 경우 ( XX일 )
                return new int[] {0, 0, targetDay - todayDay};
            }
        }
    }
}
