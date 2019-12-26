package com.music.server.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;


@Data
@Accessors(chain = true)
public class Artist {
    BigInteger aid;
    String name;
}
