package com.groupms.services;

import com.groupms.domain.entities.Group;

public interface GroupService{
    Group read(Long id);
    Group create(Group group);
}
