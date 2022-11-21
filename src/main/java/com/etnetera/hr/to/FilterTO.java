package com.etnetera.hr.to;

import com.etnetera.hr.data.enums.DeprecatedEnum;
import com.etnetera.hr.data.enums.HypeLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class FilterTO {

    private String frameworkName;
    private DeprecatedEnum deprecated;
    private List<HypeLevel> hypeLevels;

}

