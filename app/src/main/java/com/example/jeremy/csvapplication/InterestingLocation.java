package com.example.jeremy.csvapplication;

class InterestingLocation {
    String title;
    String location;
    String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return  title + "\n" +
                "Location: " + location + "\n" +
                "Description: " + description;
    }
}
