package com.itcentercrmquva.quvaitcentercrm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OrganizationSubjects {
    private Long id;
    private String name;

    @Override
    public boolean equals(Object obj) {
        OrganizationSubjects orgSub = (OrganizationSubjects) obj;
        return orgSub.id.equals(this.id) && orgSub.name.equals(this.name);
    }
}
