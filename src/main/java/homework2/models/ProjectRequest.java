package homework2.models;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectRequest {

    @SerializedName("title")
    private String title;

    @SerializedName("code")
    private String code;

    @SerializedName("description")
    private String description;

    @SerializedName("access")
    private String access;

    @SerializedName("group")
    private String group;
}
