package com.example.demo.Model.Domain;

import lombok.Data;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "JobCollection")
public class ScheduledBase {
    @TextIndexed
    private String text;

    private String url;
}
