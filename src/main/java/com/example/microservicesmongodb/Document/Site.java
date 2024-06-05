package com.example.microservicesmongodb.Document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "Sites")
@Data
public class Site {

    @Id
    private UUID id;
    private String name;
    private Location location;
    private boolean isParalympic;
    private List<UUID> sports;

    public static class Location {
        private double latitude;
        private double longitude;

        // Getters et setters
        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }

}
