package com.example.microservicesmongodb.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Document(collection = "Sports")
@Data
public class Sport {
    private UUID id;
    private String name;
    private List<Sport.Events> events;

        @Data
        public static class Events {
        @Field("event_id")
        private UUID eventId;
        private LocalDateTime date;
        @Field("site_id")
        private UUID siteId;
    }
}
