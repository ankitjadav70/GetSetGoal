package com.example.getsetgoal;

import java.io.Serializable;

public class MilestoneModel implements Serializable {

    int milestoneNumber;
    int milstonedays;
    int milestone_iscomplete=0;
    String milestoneText;
    String milestoneStartdate;
    String milestoneEnddate;


    String milestoneStatus="";
    String milestoneTime="";

    boolean isPlayed=false;

    int milestoneIsplayed=0;



    String goalName;
    public boolean isPlayed() {
        return isPlayed;
    }
    public void setPlayed(boolean played) {
        isPlayed = played;
    }


    public MilestoneModel(String goalName,int milestoneNumber, int milstonedays, String milestoneText, String milestoneStartdate, String milestoneEnddate,int milestone_iscomplete,int milestoneIsplayed,String milestoneStatus,String milestoneTime) {
        this.milestoneNumber = milestoneNumber;
        this.milstonedays = milstonedays;
        this.milestoneText = milestoneText;
        this.milestoneStartdate = milestoneStartdate;
        this.milestoneEnddate = milestoneEnddate;
        this.milestone_iscomplete=milestone_iscomplete;
        this.milestoneIsplayed=milestoneIsplayed;
        this.milestoneStatus=milestoneStatus;
        this.milestoneTime=milestoneTime;
        this.goalName=goalName;
    }

    public MilestoneModel(int milstonedays, String milestoneText) {
        this.milstonedays = milstonedays;
        this.milestoneText = milestoneText;
    }

    public String getMilestoneStatus() {
        return milestoneStatus;
    }

    public void setMilestoneStatus(String milestoneStatus) {
        this.milestoneStatus = milestoneStatus;
    }

    public String getMilestoneTime() {
        return milestoneTime;
    }

    public void setMilestoneTime(String milestoneTime) {
        this.milestoneTime = milestoneTime;
    }

    public int getMilestoneNumber() {
        return milestoneNumber;
    }

    public void setMilestoneNumber(int milestoneNumber) {
        this.milestoneNumber = milestoneNumber;
    }

    public int getMilstonedays() {
        return milstonedays;
    }

    public void setMilstonedays(int milstonedays) {
        this.milstonedays = milstonedays;
    }

    public String getMilestoneText() {
        return milestoneText;
    }

    public void setMilestoneText(String milestoneText) {
        this.milestoneText = milestoneText;
    }

    public String getMilestoneStartdate() {
        return milestoneStartdate;
    }

    public void setMilestoneStartdate(String milestoneStartdate) {
        this.milestoneStartdate = milestoneStartdate;
    }

    public String getMilestoneEnddate() {
        return milestoneEnddate;
    }

    public void setMilestoneEnddate(String milestoneEnddate) {
        this.milestoneEnddate = milestoneEnddate;
    }

    public int getMilestone_iscomplete() {
        return milestone_iscomplete;
    }

    public void setMilestone_iscomplete(int milestone_iscomplete) {
        this.milestone_iscomplete = milestone_iscomplete;
    }

    public int getMilestoneIsplayed() {
        return milestoneIsplayed;
    }

    public void setMilestoneIsplayed(int milestoneIsplayed) {
        this.milestoneIsplayed = milestoneIsplayed;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }
}
