package com.example.requests;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilterRequest {
    private List<String> name;
    private List<Integer> uuid;
    private List<LocalDate> birthDay;
    private List<String> breed;
}