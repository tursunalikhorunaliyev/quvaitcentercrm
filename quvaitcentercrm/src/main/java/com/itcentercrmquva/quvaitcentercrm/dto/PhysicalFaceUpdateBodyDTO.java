package com.itcentercrmquva.quvaitcentercrm.dto;

import java.util.Map;

public class PhysicalFaceUpdateBodyDTO {
    final int id;
    final Map<String, Object> data;

    public PhysicalFaceUpdateBodyDTO(int id, Map<String, Object> data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
