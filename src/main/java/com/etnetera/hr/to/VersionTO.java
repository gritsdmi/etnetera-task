package com.etnetera.hr.to;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
public class VersionTO {

    private final long id;
    private final String version;
    private final LocalDateTime established;

}
