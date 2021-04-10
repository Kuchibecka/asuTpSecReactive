package ru.kuchibecka.asuTpSecReactive.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Node {
    @Getter
    @Setter
    private Long obj_id;

    @Getter
    @Setter
    private int type;

    @Getter
    @Setter
    private String name;
}
