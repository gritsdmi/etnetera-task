package com.etnetera.hr.to;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class NewVersionTO {

    private long frameworkId;
    private VersionTO version;
}
