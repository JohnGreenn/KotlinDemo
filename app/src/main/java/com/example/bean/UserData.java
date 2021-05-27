package com.example.bean;

/**
 * 描述：
 * fileName：com.example.bean
 * author：Hujm
 * 添加版本：V4.2.12
 * time：2021/05/31 13:22
 */
public class UserData {

    /**
     * mobile : 15056932970
     * nickname : 胡建民
     * mem_status : 3
     * vip_qudao : 0
     * create_time : 1583912999
     * pid : 1
     * ppid : 0
     * mycode : GWL49177
     * pcode : 88888888
     * tcode : null
     * tm_id : 2419815061
     * amount : 1.46875
     * total_income : 1.46875
     * status : 1
     * sex : 0
     * usesig :
     * avatar : http://lm.spshop.cn/upload/avatar/19.png?x-oss-process=image/resize,m_fill,h_100,w_100|image/circle,r_100/format,png&rnd=1622438469
     * black_card : 1674291524
     * vip_time : 2022年05月02日
     * is_zxvip : 1
     * vip_pay_time :
     * is_vip : 1
     * upgProgress : 39
     */

    private InfoBean userInfo;
    private double balance;
    private double withdrawable;
    private double totalIncome;
    /**
     * paid_today : {"income":0,"startTime":"2021-05-31 00:00:00","endTime":"2021-05-31 23:59:59"}
     * paid_yesterday : {"income":0,"startTime":"2021-05-30 00:00:00","endTime":"2021-05-30 23:59:59"}
     * paid_this_month : {"income":0,"startTime":"2021-05-1 00:00:00","endTime":"2021-05-31 23:59:59"}
     * paid_last_month : {"income":0,"startTime":"2021-4-1 00:00:00","endTime":"2021-4-30 23:59:59"}
     * earning_this_month : {"income":0,"startTime":"2021-05-1 00:00:00","endTime":"2021-05-31 23:59:59"}
     * earning_last_month : {"income":0,"startTime":"2021-4-1 00:00:00","endTime":"2021-4-30 23:59:59"}
     */

    private IncomesBean incomes;
    /**
     * lv1 : 0
     * lv2 : 0
     * allNodeLv2 : 3
     * all : 55
     * allNodeLv3 : 52
     */

    private FansBean fans;
    private int points;
    private String sumPrice;
    private String economizePrice;

    public InfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(InfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getWithdrawable() {
        return withdrawable;
    }

    public void setWithdrawable(double withdrawable) {
        this.withdrawable = withdrawable;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public IncomesBean getIncomes() {
        return incomes;
    }

    public void setIncomes(IncomesBean incomes) {
        this.incomes = incomes;
    }

    public FansBean getFans() {
        return fans;
    }

    public void setFans(FansBean fans) {
        this.fans = fans;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(String sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getEconomizePrice() {
        return economizePrice;
    }

    public void setEconomizePrice(String economizePrice) {
        this.economizePrice = economizePrice;
    }


    public static class IncomesBean {
        /**
         * income : 0
         * startTime : 2021-05-31 00:00:00
         * endTime : 2021-05-31 23:59:59
         */

        private PaidTodayBean paid_today;
        /**
         * income : 0
         * startTime : 2021-05-30 00:00:00
         * endTime : 2021-05-30 23:59:59
         */

        private PaidYesterdayBean paid_yesterday;
        /**
         * income : 0
         * startTime : 2021-05-1 00:00:00
         * endTime : 2021-05-31 23:59:59
         */

        private PaidThisMonthBean paid_this_month;
        /**
         * income : 0
         * startTime : 2021-4-1 00:00:00
         * endTime : 2021-4-30 23:59:59
         */

        private PaidLastMonthBean paid_last_month;
        /**
         * income : 0
         * startTime : 2021-05-1 00:00:00
         * endTime : 2021-05-31 23:59:59
         */

        private EarningThisMonthBean earning_this_month;
        /**
         * income : 0
         * startTime : 2021-4-1 00:00:00
         * endTime : 2021-4-30 23:59:59
         */

        private EarningLastMonthBean earning_last_month;

        public PaidTodayBean getPaid_today() {
            return paid_today;
        }

        public void setPaid_today(PaidTodayBean paid_today) {
            this.paid_today = paid_today;
        }

        public PaidYesterdayBean getPaid_yesterday() {
            return paid_yesterday;
        }

        public void setPaid_yesterday(PaidYesterdayBean paid_yesterday) {
            this.paid_yesterday = paid_yesterday;
        }

        public PaidThisMonthBean getPaid_this_month() {
            return paid_this_month;
        }

        public void setPaid_this_month(PaidThisMonthBean paid_this_month) {
            this.paid_this_month = paid_this_month;
        }

        public PaidLastMonthBean getPaid_last_month() {
            return paid_last_month;
        }

        public void setPaid_last_month(PaidLastMonthBean paid_last_month) {
            this.paid_last_month = paid_last_month;
        }

        public EarningThisMonthBean getEarning_this_month() {
            return earning_this_month;
        }

        public void setEarning_this_month(EarningThisMonthBean earning_this_month) {
            this.earning_this_month = earning_this_month;
        }

        public EarningLastMonthBean getEarning_last_month() {
            return earning_last_month;
        }

        public void setEarning_last_month(EarningLastMonthBean earning_last_month) {
            this.earning_last_month = earning_last_month;
        }

        public static class PaidTodayBean {
            private int income;
            private String startTime;
            private String endTime;

            public int getIncome() {
                return income;
            }

            public void setIncome(int income) {
                this.income = income;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }
        }

        public static class PaidYesterdayBean {
            private int income;
            private String startTime;
            private String endTime;

            public int getIncome() {
                return income;
            }

            public void setIncome(int income) {
                this.income = income;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }
        }

        public static class PaidThisMonthBean {
            private int income;
            private String startTime;
            private String endTime;

            public int getIncome() {
                return income;
            }

            public void setIncome(int income) {
                this.income = income;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }
        }

        public static class PaidLastMonthBean {
            private int income;
            private String startTime;
            private String endTime;

            public int getIncome() {
                return income;
            }

            public void setIncome(int income) {
                this.income = income;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }
        }

        public static class EarningThisMonthBean {
            private int income;
            private String startTime;
            private String endTime;

            public int getIncome() {
                return income;
            }

            public void setIncome(int income) {
                this.income = income;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }
        }

        public static class EarningLastMonthBean {
            private int income;
            private String startTime;
            private String endTime;

            public int getIncome() {
                return income;
            }

            public void setIncome(int income) {
                this.income = income;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }
        }
    }

    public static class FansBean {
        private int lv1;
        private int lv2;
        private int allNodeLv2;
        private int all;
        private int allNodeLv3;

        public int getLv1() {
            return lv1;
        }

        public void setLv1(int lv1) {
            this.lv1 = lv1;
        }

        public int getLv2() {
            return lv2;
        }

        public void setLv2(int lv2) {
            this.lv2 = lv2;
        }

        public int getAllNodeLv2() {
            return allNodeLv2;
        }

        public void setAllNodeLv2(int allNodeLv2) {
            this.allNodeLv2 = allNodeLv2;
        }

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }

        public int getAllNodeLv3() {
            return allNodeLv3;
        }

        public void setAllNodeLv3(int allNodeLv3) {
            this.allNodeLv3 = allNodeLv3;
        }
    }
}
