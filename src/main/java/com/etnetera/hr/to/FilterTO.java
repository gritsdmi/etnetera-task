package com.etnetera.hr.to;

import com.etnetera.hr.data.enums.DeprecatedEnum;
import com.etnetera.hr.data.enums.HypeLevel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FilterTO {

    private String frameworkName;
    private DeprecatedEnum deprecated;
    private List<HypeLevel> hypeLevels;

}

