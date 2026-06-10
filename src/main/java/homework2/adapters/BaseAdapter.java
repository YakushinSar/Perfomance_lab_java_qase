package homework2.adapters;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static homework2.constants.Constants.BASE_URL;
import static homework2.constants.Constants.TOKEN;

public class BaseAdapter {

    public static RequestSpecification spec = new RequestSpecBuilder()
            .setContentType("application/json")
            .setBaseUri(BASE_URL)
            .setBasePath("/v1")
            .addHeader("Token", TOKEN)
            .build();
}
