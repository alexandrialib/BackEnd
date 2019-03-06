package edu.eci.arsw.alexandria.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Article {
    @NonNull private String title;
    @NonNull private String content;
    private List<Version> past;
    private Version actual;


}
