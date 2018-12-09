package com.example.rus.tfproject.network.DTO;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestQuestion implements Parcelable {

    @SerializedName("id")
    public Integer id;
    @SerializedName("position")
    public Integer position;
    @SerializedName("problem")
    public Problem problem;
    @SerializedName("status")
    public Object status;
    @SerializedName("attempts_left")
    public Integer attemptsLeft;
    @SerializedName("report_attempts_left")
    public Integer reportAttemptsLeft;
    @SerializedName("last_submission")
    public LastSubmission lastSubmission;

    protected TestQuestion(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            position = null;
        } else {
            position = in.readInt();
        }
        if (in.readByte() == 0) {
            attemptsLeft = null;
        } else {
            attemptsLeft = in.readInt();
        }
        if (in.readByte() == 0) {
            reportAttemptsLeft = null;
        } else {
            reportAttemptsLeft = in.readInt();
        }
    }

    public static final Creator<TestQuestion> CREATOR = new Creator<TestQuestion>() {
        @Override
        public TestQuestion createFromParcel(Parcel in) {
            return new TestQuestion(in);
        }

        @Override
        public TestQuestion[] newArray(int size) {
            return new TestQuestion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        if (position == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(position);
        }
        if (attemptsLeft == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(attemptsLeft);
        }
        if (reportAttemptsLeft == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(reportAttemptsLeft);
        }
    }
}
