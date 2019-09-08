package com.discovery.registry.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationPayload{
    private String[] tags;
    private String[] others;
    private String hostname;
}
