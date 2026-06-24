package homework2.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CaseResponse {

    @SerializedName("status")
    private Boolean status;

    @SerializedName("result")
    private CaseResult result;

    @Data
    public static class CaseResult {
        @SerializedName("id")
        private Integer id;
    }
}
