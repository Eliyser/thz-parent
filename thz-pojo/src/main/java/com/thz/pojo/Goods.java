package com.thz.pojo;

/**
 * @Author haien
 * @Description 受检物质类
 * @Date 2018/11/26
 **/
public class Goods {
    private long id;
    //温度
    private String tem;
    //湿度
    private String hum;
    //物质名
    private String name;
    //次数
    private long number;
    //类型
    private String type;
    //时间
    private String reportDateTime; //改date？
    private String verticalAxis;
    private String horizontalAxis;
    //频率
    private String frequencyResolution;
    private double deltaTime;
    private long cumulatedNumber;
    private double masterSeedLaser;
    private double eSeedLaser;
    private double biasVoltage;
    private long biasGain;
    private double sampleThickness;
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;
    //x轴数据
    private String xAxis;
    //y轴数据
    private String result;

    public Goods() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReportDateTime() {
        return reportDateTime;
    }

    public void setReportDateTime(String reportDateTime) {
        this.reportDateTime = reportDateTime;
    }

    public String getVerticalAxis() {
        return verticalAxis;
    }

    public void setVerticalAxis(String verticalAxis) {
        this.verticalAxis = verticalAxis;
    }

    public String getHorizontalAxis() {
        return horizontalAxis;
    }

    public void setHorizontalAxis(String horizontalAxis) {
        this.horizontalAxis = horizontalAxis;
    }

    public String getFrequencyResolution() {
        return frequencyResolution;
    }

    public void setFrequencyResolution(String frequencyResolution) {
        this.frequencyResolution = frequencyResolution;
    }

    public double getDeltaTime() {
        return deltaTime;
    }

    public void setDeltaTime(double deltaTime) {
        this.deltaTime = deltaTime;
    }

    public long getCumulatedNumber() {
        return cumulatedNumber;
    }

    public void setCumulatedNumber(long cumulatedNumber) {
        this.cumulatedNumber = cumulatedNumber;
    }

    public double getMasterSeedLaser() {
        return masterSeedLaser;
    }

    public void setMasterSeedLaser(double masterSeedLaser) {
        this.masterSeedLaser = masterSeedLaser;
    }

    public double geteSeedLaser() {
        return eSeedLaser;
    }

    public void seteSeedLaser(double eSeedLaser) {
        this.eSeedLaser = eSeedLaser;
    }

    public double getBiasVoltage() {
        return biasVoltage;
    }

    public void setBiasVoltage(double biasVoltage) {
        this.biasVoltage = biasVoltage;
    }

    public long getBiasGain() {
        return biasGain;
    }

    public void setBiasGain(long biasGain) {
        this.biasGain = biasGain;
    }

    public double getSampleThickness() {
        return sampleThickness;
    }

    public void setSampleThickness(double sampleThickness) {
        this.sampleThickness = sampleThickness;
    }

    public double getxMin() {
        return xMin;
    }

    public void setxMin(double xMin) {
        this.xMin = xMin;
    }

    public double getxMax() {
        return xMax;
    }

    public void setxMax(double xMax) {
        this.xMax = xMax;
    }

    public double getyMin() {
        return yMin;
    }

    public void setyMin(double yMin) {
        this.yMin = yMin;
    }

    public double getyMax() {
        return yMax;
    }

    public void setyMax(double yMax) {
        this.yMax = yMax;
    }

    public String getxAxis() {
        return xAxis;
    }

    public void setxAxis(String xAxis) {
        this.xAxis = xAxis;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
