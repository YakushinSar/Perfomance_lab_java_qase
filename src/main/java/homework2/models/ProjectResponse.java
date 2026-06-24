package homework2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ProjectResponse {

    @SerializedName("status")
    private Boolean status;

    @SerializedName("result")
    private ProjectResult result;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ProjectResult {
        @SerializedName("code")
        private String code;
    }
}
