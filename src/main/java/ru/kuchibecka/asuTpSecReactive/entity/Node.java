package ru.kuchibecka.asuTpSecReactive.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Node {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private List<String> labels;

    @Getter
    @Setter
    private Map<String, java.lang.Object> properties;
}
