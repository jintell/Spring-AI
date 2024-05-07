package com.jade.platform.springaiintro.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * @Author: Josiah Adetayo
 * @Email: josleke@gmail.com, josiah.adetayo@meld-tech.com
 * @Date: 5/7/24
 */
public record GetCapitalResponse(@JsonPropertyDescription("he name of the capital") String capital,
                                 @JsonPropertyDescription("name of ") String presidentOrGovernor) {
}
