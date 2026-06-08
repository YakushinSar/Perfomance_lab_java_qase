package lesson2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProjectResponse {

    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("result")
    @Expose
    public Result result;
}
