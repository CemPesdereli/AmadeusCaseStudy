package com.cem.flight.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;


@OpenAPIDefinition(security = {@SecurityRequirement(name = "basicAuth")}

)
@SecurityScheme(

        name = "basicAuth",
        description = "normal auth",
        scheme = "basic",
        type = SecuritySchemeType.HTTP
)
public class OpenApiConfig {



}
