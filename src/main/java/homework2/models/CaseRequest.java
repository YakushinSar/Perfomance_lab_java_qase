package homework2.models;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaseRequest {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("preconditions")
    private String preconditions;

    @SerializedName("postconditions")
    private String postconditions;

    @SerializedName("severity")
    private Integer severity;

    @SerializedName("priority")
    private Integer priority;

    @SerializedName("type")
    private Integer type;
}
