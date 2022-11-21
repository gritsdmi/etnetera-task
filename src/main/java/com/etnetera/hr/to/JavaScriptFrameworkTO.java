package com.etnetera.hr.to;

import com.etnetera.hr.data.enums.HypeLevel;
import com.etnetera.hr.data.Version;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@ToString
@Getter
public class JavaScriptFrameworkTO {

    private String name;
    private List<Version> version;
    private Date deprecationDate;
    private HypeLevel hypeLevel;

}
