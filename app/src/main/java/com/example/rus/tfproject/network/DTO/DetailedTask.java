package com.example.rus.tfproject.network.DTO;

import com.google.gson.annotations.SerializedName;

public class DetailedTask implements Comparable<DetailedTask> {
    @SerializedName("id")
    public Integer id;
    @SerializedName("title")
    public String title;
    @SerializedName("task_type")
    public String taskType;
    @SerializedName("max_score")
    public String maxScore;
    @SerializedName("deadline_date")
    public String deadlineDate;
    @SerializedName("contest_info")
    public ContestInfo contestInfo;
    @SerializedName("short_name")
    public String shortName;

    @Override
    public int compareTo(DetailedTask detailedTask) {
        if (this.contestInfo.contestStatus.status.equals("announcement") && !detailedTask.contestInfo.contestStatus.status.equals("announcement")){
            return -1;
        } else if (this.contestInfo.contestStatus.status.equals(detailedTask.contestInfo.contestStatus.status)){
            return 0;
        } else if (!this.contestInfo.contestStatus.status.equals("announcement") && detailedTask.contestInfo.contestStatus.status.equals("announcement")){
            return 1;
        } else if (this.contestInfo.contestStatus.status.equals("ongoing") && !detailedTask.contestInfo.contestStatus.status.equals("ongoing")) {
            return -1;
        } else if (!this.contestInfo.contestStatus.status.equals("ongoing") && detailedTask.contestInfo.contestStatus.status.equals("ongoing")){
            return 1;
        } else if (this.contestInfo.contestStatus.status.equals("contest_review") && !detailedTask.contestInfo.contestStatus.status.equals("contest_review")) {
            return -1;
        } else {
            return 1;
        }
    }
}
