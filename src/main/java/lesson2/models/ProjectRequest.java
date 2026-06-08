package lesson2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectRequest {

    // создаются переменные запроса
    // тут пишется название как в json, например может отличаться от джавы, например title_code
    @SerializedName("title")
    // в серилизации участвуют только те параметры которые помечены аннотацией
    @Expose
    // тут пишется название как в java
    private String title;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("access")
    @Expose
    private String access;
    @SerializedName("group")
    @Expose
    private String group;

}
