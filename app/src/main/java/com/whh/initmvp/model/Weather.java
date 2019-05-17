package com.whh.initmvp.model;

import java.util.List;

/**
 * Created by wuhuihui on 2019/5/17.
 */

public class Weather {

    private String time;
    private CityInfo cityInfo;
    private String date;
    private String message;
    private int status;
    private Data data;

    public Weather(String time, CityInfo cityInfo, String date, String message, int status, Data data) {
        this.time = time;
        this.cityInfo = cityInfo;
        this.date = date;
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    class CityInfo {
        private String city;
        private String cityId;
        private String parent;
        private String updateTime;

        public CityInfo(String city, String cityId, String parent, String updateTime) {
            this.city = city;
            this.cityId = cityId;
            this.parent = parent;
            this.updateTime = updateTime;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        @Override
        public String toString() {
            return "CityInfo{" +
                    "city='" + city + '\'' +
                    ", cityId='" + cityId + '\'' +
                    ", parent='" + parent + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    '}';
        }
    }

    class Data {
        private String shidu;
        private int pm25;
        private int pm10;
        private String quality;
        private String wendu;
        private String ganmao;
        private Yesterday yesterday;
        private List<DayWeather> forecast;

        /**
         * {
         * "date": "16",
         * "sunrise": "05:00",
         * "high": "高温 32.0℃",
         * "low": "低温 19.0℃",
         * "sunset": "19:22",
         * "aqi": 114,
         * "ymd": "2019-05-16",
         * "week": "星期四",
         * "fx": "西南风",
         * "fl": "3-4级",
         * "type": "晴",
         * "notice": "愿你拥有比阳光明媚的心情"
         * }
         */
        class DayWeather {
            private String date;
            private String sunrise;
            private String high;
            private String low;
            private String sunset;
            private int aqi;
            private String ymd;
            private String week;
            private String fx;
            private String fl;
            private String type;
            private String notice;

            public DayWeather(String date, String sunrise, String high, String low, String sunset, int aqi, String ymd, String week, String fx, String fl, String type, String notice) {
                this.date = date;
                this.sunrise = sunrise;
                this.high = high;
                this.low = low;
                this.sunset = sunset;
                this.aqi = aqi;
                this.ymd = ymd;
                this.week = week;
                this.fx = fx;
                this.fl = fl;
                this.type = type;
                this.notice = notice;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public int getAqi() {
                return aqi;
            }

            public void setAqi(int aqi) {
                this.aqi = aqi;
            }

            public String getYmd() {
                return ymd;
            }

            public void setYmd(String ymd) {
                this.ymd = ymd;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            @Override
            public String toString() {
                return "\n" + "DayWeather{" +
                        "date='" + date + '\'' +
                        ", sunrise='" + sunrise + '\'' +
                        ", high='" + high + '\'' +
                        ", low='" + low + '\'' +
                        ", sunset='" + sunset + '\'' +
                        ", aqi=" + aqi +
                        ", ymd='" + ymd + '\'' +
                        ", week='" + week + '\'' +
                        ", fx='" + fx + '\'' +
                        ", fl='" + fl + '\'' +
                        ", type='" + type + '\'' +
                        ", notice='" + notice + '\'' +
                        '}';
            }
        }

        class Yesterday extends DayWeather {

            public Yesterday(String date, String sunrise, String high, String low, String sunset, int aqi, String ymd, String week, String fx, String fl, String type, String notice) {
                super(date, sunrise, high, low, sunset, aqi, ymd, week, fx, fl, type, notice);
            }

        }

        public Data(String shidu, int pm25, int pm10, String quality, String wendu,
                    String ganmao, Yesterday yesterday, List<DayWeather> forecast) {
            this.shidu = shidu;
            this.pm25 = pm25;
            this.pm10 = pm10;
            this.quality = quality;
            this.wendu = wendu;
            this.ganmao = ganmao;
            this.yesterday = yesterday;
            this.forecast = forecast;
        }

        public String getShidu() {
            return shidu;
        }

        public void setShidu(String shidu) {
            this.shidu = shidu;
        }

        public int getPm25() {
            return pm25;
        }

        public void setPm25(int pm25) {
            this.pm25 = pm25;
        }

        public int getPm10() {
            return pm10;
        }

        public void setPm10(int pm10) {
            this.pm10 = pm10;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public Yesterday getYesterday() {
            return yesterday;
        }

        public void setYesterday(Yesterday yesterday) {
            this.yesterday = yesterday;
        }

        public List<DayWeather> getForecast() {
            return forecast;
        }

        public void setForecast(List<DayWeather> forecast) {
            this.forecast = forecast;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "shidu='" + shidu + '\'' +
                    ", pm25=" + pm25 +
                    ", pm10=" + pm10 +
                    ", quality='" + quality + '\'' +
                    ", wendu='" + wendu + '\'' +
                    ", ganmao='" + ganmao + '\'' +
                    ",\n\n dayWeather=" + yesterday +
                    ",\n\n dayWeathers=" + forecast +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "Weather{" +
                "time='" + time + '\'' +
                ", cityInfo=" + cityInfo +
                ", date='" + date + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}
