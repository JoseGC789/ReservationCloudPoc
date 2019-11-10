package com.groupms.services;

import com.groupms.domain.entities.Group;
import com.groupms.persistence.GroupRepository;
import org.springframework.stereotype.Service;

@Service
public class SqlGroupService implements GroupService{
    private final GroupRepository repository;

    public SqlGroupService(GroupRepository repository){
        this.repository = repository;
    }

    @Override
    public Group read(Long id){
        return repository.findById(id).orElseGet(Group::empty);
    }

    @Override
    public Group create(Group group){
        return repository.save(group);
    }
}
