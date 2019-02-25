package com.example.schedule.Info;

import org.litepal.crud.DataSupport;

/**
 * Created by buynow1 on 2018/10/13.
 */

public class PlanInfo extends DataSupport{
    //the Plan is the item of RecyclerView (image, content, times, alarm)
    private int id;
    private int imageId;
    private String type;
    private String content;
    private String alarm;
    private int finished;

    //Constructor

    public PlanInfo (){}

    public PlanInfo(int imageId, String type, String content, String alarm) {
        this.imageId = imageId;
        this.type = type;
        this.content = content;
        this.alarm = alarm;
        this.finished = 0;
    }

    public PlanInfo(int id, int imageId, String content, String type, String alarm) {
        this.id = id;
        this.imageId = imageId;
        this.content = content;
        this.type = type;
        this.alarm = alarm;
        this.finished = 0;
    }


    //Getting and Setting


    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public int getImageId() {
        return imageId;
    }

    public String getType() {
        return type;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    //ToString


    @Override
    public String toString() {
        return "PlanInfo{" +
                "id=" + id +
                ", imageId=" + imageId +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", alarm='" + alarm + '\'' +
                '}';
    }
}
